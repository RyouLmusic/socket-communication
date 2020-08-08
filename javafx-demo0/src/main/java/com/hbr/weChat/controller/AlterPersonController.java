package com.hbr.weChat.controller;

import com.hbr.weChat.config.DialogShow;
import com.hbr.weChat.config.WindowParam;
import com.hbr.weChat.model.User;
import com.hbr.weChat.view.HeadPortraitView;
import com.hbr.weChat.view.HomePageView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class AlterPersonController implements Initializable {
    @FXML private Pane AlterPerson;

    @FXML private Button background;
    @FXML private Button head;

    @FXML private Button replace;//唤出更换头像的页面

    @FXML private Button cancel;

    @Autowired
    HomePageView homePageView;
    @Autowired
    HeadPortraitView headPortraitView;

    @Autowired
    LoginController loginAccountInfo;
    Stage stage;

    public void cancel(){
        cancel.setOnAction(event -> {
            WindowParam.reSize(DialogShow.stage, WindowParam.Home_Page_Height, WindowParam.Home_Page_Width);
            if(stage != null)
                stage.close();
            DialogShow.show(homePageView);
        });
    }
    public void replace(){
        replace.setOnAction(event -> {
            if(stage == null){
                stage = new Stage();
                stage.initStyle(stage.getStyle().TRANSPARENT);
            }
            DialogShow.secondStage = stage;

            if (stage.getScene() == null) {
                stage.setScene(new Scene(headPortraitView.getView()));
            } else {
                stage.getScene().setRoot(headPortraitView.getView());
            }
            MoveController.move(stage, stage.getScene().getRoot());
            stage.setScene(stage.getScene());
            stage.show();
        });
    }

    private void setOldInfo(){
        User user = loginAccountInfo.getOwnInfo();
        background.setStyle(String.format("-fx-background-image: url('/css/Image/head/%s.jpg')", user.getHead()));
        head.setStyle(String.format("-fx-background-image: url('/css/Image/head/%s.jpg')", user.getHead()));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancel();
        replace();
        setOldInfo();
    }
}
