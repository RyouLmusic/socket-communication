package com.hbr.weChat.view;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.Vector;

public class FriendsListItem extends Pane {
    private Button head;
    private Label information;
    private Button send;
    private String friendHead; //头像的名称
    private Button MsgTip; //消息提醒的图片
    private Button state;  //状态，在线或是离线
    private String friendAccount;
    Vector<MenuItem> items;

    public FriendsListItem(String headIcon, String remark, String account, int status){
        compose();
        setCSS();
        setSize();
        setLayout();
        items = new Vector<>();
        items.add(new MenuItem(""));
        items.add(new MenuItem("好友资料"));
        items.add(new MenuItem("清除聊天记录"));
        items.add(new MenuItem("删除好友"));
        setHead(headIcon);
        setText(remark);
        friendAccount = account;
        setMenuItem();
        setStates(status);
//        sent();
    }


    /**
     * 组合在一起
     */
    private void compose(){
        head = new Button();
        information = new Label();
        MsgTip = new Button();
        state = new Button();
        this.getChildren().add(head);
        this.getChildren().add(state);
        this.getChildren().add(information);
        this.getChildren().add(MsgTip);

        head.setTooltip(new Tooltip("查看好友资料"));
        send = new Button();

        this.getChildren().add(send);
    }

    /**
     * 设置css样式
     */
    private void setCSS(){
        this.getStylesheets().add("css/mainWindow.css");
        state.getStyleClass().add("outline");
        MsgTip.getStyleClass().add("no-MsgTip");
        send.getStyleClass().add("sendMsg");
        head.getStyleClass().add("head");
        information.getStyleClass().add("information");
        this.getStyleClass().add("ListItem");
    }

    /**
     * 初始化组件大小
     */
    private void setSize(){
        MsgTip.setPrefSize(20,20);
        MsgTip.setLayoutY(15);
        MsgTip.setLayoutX(275);
        state.setPrefSize(15,15);
        state.setLayoutX(55);
        state.setLayoutY(30);
        this.setPrefSize(295,50);
        head.setPrefSize(46,46);
        information.setPrefSize(150,30);
        information.setLayoutX(55);
        send.setPrefSize(295,50);
    }

    /**
     * 初始化位置
     */
    private void setLayout(){
        head.setLayoutX(2);
        head.setLayoutY(2);
        send.setLayoutX(0);
        send.setLayoutY(0);

        information.setLayoutY(1);
    }

    private void setStates(int status) {
        if(status == 0){
            setOutline();
        }
        if(status == 1){
            setOnline();
        }
    }

    public void setText(String text){
        information.setText(text);
    }
    /**
     * 好友上线状态
     */
    public void setOnline(){
        state.getStyleClass().clear();
        state.getStyleClass().add("online");
    }

    /**
     * 好友下线状态
     */
    public void setOutline(){
        state.getStyleClass().clear();
        state.getStyleClass().add("outline");
    }


    /**
     * 好友项右键菜单
     */
    public void setMenuItem(){
        ContextMenu menu = new ContextMenu();
        for(MenuItem item: items){
            menu.getItems().add(item);
        }
        send.setContextMenu(menu);
    }


    /**
     * 消息提示
     * @param value
     */
    //TODO 这个方法要在外边实现和调用
    public void addMsgTip(int value){
        MsgTip.getStyleClass().clear();
        MsgTip.getStyleClass().add("MsgTip");
        MsgTip.setText(" " + value);
    }

    public void setHead(String head){
        setHeadPortrait(this.head,head);
        friendHead = head;
    }
    public void setHeadPortrait(Button button, String head){
        button.setStyle(String.format("-fx-background-image: url('/css/Image/head/%s.jpg')",head));
    }

    /**
     * 在各个聊天画面跳转
     */
    public void sent(){
        send.setOnAction(event -> System.out.println("++++++++++"));
    }

    public Button getSend(){
        return send;
    }

    public String getFriendAccount() {
        return friendAccount;
    }

    public String getFriendHead() {
        return friendHead;
    }
}
