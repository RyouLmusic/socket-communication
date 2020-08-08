package com.hbr.weChat.controller;

import com.hbr.weChat.JavafxDemo0Application;
import com.hbr.weChat.config.*;
import com.hbr.weChat.model.Message;
import com.hbr.weChat.model.User;
import com.hbr.weChat.service.FriendsService;
import com.hbr.weChat.socket.ManageClientSocket;
import com.hbr.weChat.socket.SocketUtil;
import com.hbr.weChat.view.ChatListItem;
import com.hbr.weChat.view.FriendsListItem;
import com.hbr.weChat.view.HeadPortraitView;
import com.hbr.weChat.view.HomePageView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class MainWindowController implements Initializable {
    @FXML
    private Pane MainWindow;
    @FXML
    private Button quit1;
    @FXML
    private Button minimiser1;

    @FXML private Label myAccount;
    @FXML private Label myName;
    @FXML private Label myAddress;
    @FXML private Label myPhone;
    @FXML private Button more;



    @FXML private TextField input;
    @FXML private Button send;

    @FXML private Button individual;//头像

    @FXML private Pane bgPane;

    @FXML
    ListView<Pane> FriendsList;//头像列表
    @FXML
    ListView<Pane> ChatList;//聊天列表


    @Autowired
    JavafxDemo0Application application;


    @Autowired
    LoginController loginAccount;

    @Autowired
    FriendsService friendsService;

    @Autowired
    HeadPortraitView headPortraitView;

    @Autowired
    HomePageView homePageView;

    String friendAccount;

    ObservableList<Pane> data = FXCollections.observableArrayList();

    private void quit() {
        quit1.setOnAction(event -> {
            if (DialogShow.stage != null){
                DialogShow.stage.close();
            }
            application.getStage().close();
        });
    }

    private void minimize(){
        minimiser1.setOnAction(event -> application.getStage().setIconified(true));
    }

    /**
     * 显示个人信息
     */
    public void serOwnInfo(){
        User user = loginAccount.getOwnInfo();
        myAccount.setText(user.getAccount());
        myName.setText(user.getName());
        myAddress.setText(user.getAddress());
        myPhone.setText(user.getPhone());
        individual.setStyle(String.format("-fx-background-image: url('/css/Image/head/%s.jpg')", user.getHead()));
    }

    /**
     * 显示更详细的个人信息（自己的）
     */
    public void moreInfo(){
        more.setOnAction(event -> {
            Stage stage = DialogShow.show(homePageView);
            MoveController.move(DialogShow.stage, homePageView.getView());
            WindowParam.reSize(stage, WindowParam.Home_Page_Height, WindowParam.Home_Page_Width);
            stage.show();
            System.out.println(DialogShow.headInfo);
        });
    }
    /**
     * 在listView显示好友
     * 且给每个人新建chatListView
     */
    private void showFriends() {
//        List<User> users = friendsService.selectFriends(loginAccount.getAccount(), loginAccount.getPassword());
        List<User> users = loginAccount.getUsers();
        for(User user: users){
            FriendsListItem friendsListItem = new FriendsListItem(user.getHead(), user.getName(), user.getAccount(), user.getStatus());
            data.add(friendsListItem);
        }
        FriendsList.setItems(data);

        /**
         * 选中好友
         */
        for(Pane d: data){
            ((FriendsListItem) d).getSend().setOnAction(event -> {
                FriendsList.getSelectionModel().select(d);
                friendAccount = ((FriendsListItem) d).getFriendAccount();
                // TODO 进行操作

                ChatList.getItems().remove(0,ChatList.getItems().size());//删除其他人在ChatList上的消息


                List<Msg> msg =  ManageMessage.getMsg(((FriendsListItem) d).getFriendAccount());
                for (Msg message: msg){
                    if (message.getMsgType() == 1){
                        addRight(loginAccount.getOwnInfo().getHead(), message.getMsg());
                    }else if(message.getMsgType() == 0){
                        addLeft(((FriendsListItem) d).getFriendHead(), message.getMsg());
                    }
                }

            });
        }
        //TODO 还没有做完
        FriendsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });
    }


    /**
     * 添加气泡
     * @param head
     * @param Msg
     */
    public void addRight(String head,String Msg){
        ChatList.getItems().add(new ChatListItem().Right(head,Msg, Tool.getWidth(Msg), Tool.getHeight(Msg)));
//        ChatList.getItems().removeAll();
    }
    public void addLeft(String head,String Msg){
        ChatList.getItems().add(new ChatListItem().Left(head,Msg, Tool.getWidth(Msg),Tool.getHeight(Msg)));
    }
    /**
     * 发送消息
     */
    private void send(){
        send.setOnAction(event -> {
            String msg = input.getText();
            Msg m = new Msg(1, msg);
            if (friendAccount == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("请先选择好友");
                alert.show();

            }else {
                ManageMessage.addMsg(friendAccount, m);
                input.setText("");
                Message message = new Message();
                message.setSetMessageAccount(loginAccount.getOwnInfo().getAccount());
                message.setGetMessageAccount(friendAccount);
                message.setHead(loginAccount.getOwnInfo().getHead());
                message.setMessageContent(msg);
                Object o = message;
                SocketUtil.sendMessage(ManageClientSocket.getClientSocketThread(loginAccount.getOwnInfo().getAccount()).getSocket(), o);
                addRight(loginAccount.getOwnInfo().getHead(), msg);
            }
        });
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MoveController.move(AbstractJavaFxApplicationSupport.getStage(), MainWindow);
        quit();
        minimize();
        showFriends();
        serOwnInfo();
        moreInfo();
        send();
        MainWindow.getStylesheets().add("/css/mainWindow.css");

    }


}
