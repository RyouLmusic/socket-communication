package com.hbr.weChat.controller;

import com.hbr.weChat.config.DialogShow;
import com.hbr.weChat.config.WindowParam;
import com.hbr.weChat.model.User;
import com.hbr.weChat.view.AlterPersonView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class HomePageController implements Initializable {

    @FXML private Pane Homepage;
    @FXML private Button quit1;
    @FXML private Button minimiser1;
    @FXML private Button background;

    @FXML private Button head;
    @FXML private Button alter;
    @FXML private Label account;
    @FXML private TextArea label;

    @FXML private TextField name;
    @FXML private TextField address;
    @FXML private TextField sex;
    @FXML private TextField age;
    @FXML private TextField phone;

    @Autowired
    AlterPersonView alterPersonView;

    @Autowired
    LoginController loginAccountInfo;

    /**
     * 基本操作
     */
    private void quit() {
        quit1.setOnAction(event -> {
            DialogShow.stage.close();
            if (DialogShow.stage != null){
                DialogShow.stage.close();
            }
        });
    }

    public void setInfo(){
        User user = loginAccountInfo.getOwnInfo();
        background.setStyle(String.format("-fx-background-image: url('/css/Image/head/%s.jpg')", user.getHead()));
        head.setStyle(String.format("-fx-background-image: url('/css/Image/head/%s.jpg')", user.getHead()));
        account.setText(user.getAccount());
        label.setText(user.getLabel());
        name.setText(user.getName());
        address.setText(user.getAddress());
        sex.setText(user.getSex());
        age.setText(user.getAge()+"");
        phone.setText(user.getPhone());
    }
    private void minimize(){
        minimiser1.setOnAction(event -> DialogShow.stage.setIconified(true));
    }

    private void alter(){
        alter.setOnAction(event -> {
            WindowParam.reSize(DialogShow.stage, WindowParam.Alter_Person_height, WindowParam.Alter_Person_Width);
            MoveController.move(DialogShow.stage, alterPersonView.getView());
            DialogShow.show(alterPersonView);
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quit();
        minimize();
        alter();
        setInfo();
    }
}
