package com.hbr.weChat.controller;

import com.hbr.weChat.JavafxDemo0Application;
import com.hbr.weChat.config.DialogShow;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class HeadPortraitController implements Initializable {

    @FXML
    private Pane HeadPortrait;
    @FXML
    private Button quit1;
    @FXML
    private Button minimiser1;

    @FXML private Button head1;
    @FXML private Button head2;
    @FXML private Button head3;
    @FXML private Button head4;
    @FXML private Button head5;
    @FXML private Button head6;
    @FXML private Button head7;
    @FXML private Button head8;
    @FXML private Button head9;
    @FXML private Button head10;

    @FXML private RadioButton one;
    @FXML private RadioButton two;
    @FXML private RadioButton three;
    @FXML private RadioButton four;
    @FXML private RadioButton five;
    @FXML private RadioButton six;
    @FXML private RadioButton seven;
    @FXML private RadioButton eight;
    @FXML private RadioButton nine;
    @FXML private RadioButton ten;

    final ToggleGroup group = new ToggleGroup();
    @FXML
    private Button submit;

    @Autowired
    RegisterController registerController;
    /**
     * 基本操作
     */
    private void quit(Stage stage) {
        quit1.setOnAction(event -> {
            if (stage != null){
                stage.close();
            }
        });
    }

    private void minimize(Stage stage){
        minimiser1.setOnAction(event -> stage.setIconified(true));
    }

    /**
     * 对RadioButton进行操作，只可以选择一个///          group.getSelectedToggle().getUserData());
     */
    private void bindRadioButton(){
        one.setToggleGroup(group);two.setToggleGroup(group);three.setToggleGroup(group);
        four.setToggleGroup(group);five.setToggleGroup(group);six.setToggleGroup(group);
        seven.setToggleGroup(group);eight.setToggleGroup(group);nine.setToggleGroup(group);ten.setToggleGroup(group);


        one.setUserData(head1.getId());two.setUserData(head2.getId());three.setUserData(head3.getId());
        four.setUserData(head4.getId());five.setUserData(head5.getId());six.setUserData(head6.getId());
        seven.setUserData(head7.getId());eight.setUserData(head8.getId());nine.setUserData(head9.getId());ten.setUserData(head10.getId());
        group.selectToggle(ten);
    }
    private void submit(){
        submit.setOnAction(event -> {

            DialogShow.headInfo = group.getSelectedToggle().getUserData().toString();
            DialogShow.stage.close();
            registerController.getImage().setStyle(String.format("-fx-background-image: url('/css/Image/head/%s.jpg')", DialogShow.headInfo));
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(DialogShow.secondStage == null){
            minimize(DialogShow.stage);
            quit(DialogShow.stage);
            submit();
        }else if(DialogShow.secondStage != null){
            System.out.println(DialogShow.secondStage);
            minimize(DialogShow.secondStage);
            quit(DialogShow.secondStage);
        }

        bindRadioButton();
    }

}
