package com.hbr.weChat.controller;

import com.hbr.weChat.JavafxDemo0Application;
import com.hbr.weChat.config.DialogShow;
import com.hbr.weChat.config.WindowParam;

import com.hbr.weChat.view.LoginView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;


import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class ForgetController implements Initializable {

    @FXML
    private Pane Forget;
    @FXML
    private TextField account;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField rePassword;
    @FXML
    private Button quit1;
    @FXML
    private Button minimiser1;
    @FXML
    private Button reset;
    @FXML
    private Button cancel;

    @Autowired
    JavafxDemo0Application application;
    @Autowired
    LoginView loginView;


    public void quit() {
        quit1.setOnAction(event -> {
            if (DialogShow.stage != null){
                DialogShow.stage.close();
            }
            application.getStage().close();
        });
    }

    public void minimize(){
        minimiser1.setOnAction(event -> application.getStage().setIconified(true));
    }

    /**
     * 返回登录界面
     */
    @FXML
    public void cancel(){

        cancel.setOnAction(event -> {
            WindowParam.reSize(application.getStage(), WindowParam.Login_Height, WindowParam.Login_Width);
            application.showView(loginView.getClass());
        });
    }

    /**
     * 将所有的field置空（算了）
     * 直接注册
     */
    public void reset(){

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cancel();
        minimize();
        quit();
        MoveController.move(application.getStage(), Forget);

    }

    public void reSize(){
        application.getStage().setHeight(WindowParam.Forget_Height);
        application.getStage().setWidth(WindowParam.Forget_Width);
    }
}
