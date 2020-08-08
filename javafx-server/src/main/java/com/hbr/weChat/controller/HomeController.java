package com.hbr.weChat.controller;

import com.hbr.weChat.JavafxServerApplication;
import com.hbr.weChat.model.User;
import com.hbr.weChat.service.UserService;
import com.hbr.weChat.socket.ManageServerSocket;
import com.hbr.weChat.socket.ServerOfSocket;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class HomeController implements Initializable {
    @FXML
    private AnchorPane background;
    @FXML
    private Button setting;
    @FXML
    private Button quit0;

    @Autowired
    JavafxServerApplication application;



    @Autowired
    ServerOfSocket serverOfSocket;

    @Autowired
    UserService service;



    public void select(){
        setting.setOnAction(event -> {
            User user = serverOfSocket.loginServer();
            System.out.println(user);
        });
    }
    public void quit(){
        quit0.setOnAction(event -> System.out.println("关闭")); //TODO
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MoveController.move(application.getStage(), background);
        select();
        quit();
    }
}
