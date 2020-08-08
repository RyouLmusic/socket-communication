package com.hbr.weChat.controller;

import com.hbr.weChat.JavafxDemo0Application;
import com.hbr.weChat.config.DialogShow;
import com.hbr.weChat.config.WindowParam;
import com.hbr.weChat.dto.ResponseCode;
import com.hbr.weChat.model.User;
import com.hbr.weChat.service.RegisterService;
import com.hbr.weChat.view.HeadPortraitView;
import com.hbr.weChat.view.LoginView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class RegisterController implements Initializable{

    @FXML
    private Pane Register;
    @FXML
    private Button quit1;
    @FXML
    private Button minimize;
    @FXML
    private Button back;
    @FXML
    private Button register;
    @FXML
    private Button selectImage;
    @FXML
    private RadioButton selectMale;
    @FXML
    private RadioButton selectFemale;
    @FXML
    private Button image;


    @FXML
    private TextField account;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField rePassword;
    @FXML
    private TextField age;
    @FXML
    private TextField phoneNumber;



    @FXML
    private Label accountError;
    @FXML
    private Label nameError;
    @FXML
    private Label passwordError;
    @FXML
    private Label rePasswordError;
    @FXML
    private Label ageError;
    @FXML
    private Label phoneError;


    @Autowired
    JavafxDemo0Application application;
    @Autowired
    LoginView loginView;
    @Autowired
    HeadPortraitView headPortraitView;


    @Autowired
    RegisterService registerService;
    ToggleGroup group = new ToggleGroup();
    User user;

    private void quit() {
        quit1.setOnAction(event -> {
            if (DialogShow.stage != null){
                DialogShow.stage.close();
            }
            application.getStage().close();
        });
    }

    private void minimize(){
        minimize.setOnAction(event -> application.getStage().setIconified(true));
    }

    /**
     * 返回
     */
    private void back(){
        back.setOnAction(event -> {
            if (DialogShow.stage != null){
                DialogShow.stage.close();
            }
            DialogShow.headInfo = null;
            WindowParam.reSize(application.getStage(), WindowParam.Login_Height, WindowParam.Login_Width);
            application.showView(loginView.getClass());

        });
    }
    private void selectImage(){
        selectImage.setOnAction(event -> {

            Stage stage = DialogShow.show(headPortraitView);
            //button.setStyle(String.format("-fx-background-image: url('/View/Fxml/CSS/Image/%s/%s.jpg')",file,head));
            MoveController.move(DialogShow.stage, headPortraitView.getView());
            stage.show();
            //头像信息 DialogShow.headInfo
        });
    }

    private boolean userSetInfo(){
        user = new User();

        if (account.getText().equals("")){
            showError(accountError);
            return false;
        }else{
            user.setAccount(account.getText());
        }
        if (name.getText().equals("")){
            showError(nameError);
            return false;
        }else{
            user.setName(name.getText());
        }
        if (password.getText().equals("")){
            showError(passwordError);
            return false;
        }else{
            user.setPassword(password.getText());
        }
        if (rePassword.getText().equals("")){
            showError(rePasswordError);
            return false;
        }else {
        }
        if(group.getSelectedToggle() == null){
            return false;
        }else {
            user.setSex(group.getSelectedToggle().getUserData().toString());

        }
        if (age.getText().equals("")){
            showError(ageError);
            return false;
        }else{
            user.setAge(Integer.parseInt(age.getText()));

        }
        if (phoneNumber.getText().equals("")){
            showError(phoneError);
            return false;
        }else{
            user.setPhone(phoneNumber.getText());
        }
        return true;
    }
    //实现注册功能
    private void register(){
        register.setOnAction(event -> {
            hideError();
            boolean isCan = userSetInfo();
            if(DialogShow.headInfo == null){
                user.setHead("head10");
            }else {
                user.setHead(DialogShow.headInfo);
            }
            if(isCan == true){
                if (!(user.getPassword().equals(rePassword.getText()))){
                    rePasswordError.setText("两次密码不一致");
                    rePasswordError.setVisible(true);
                    isCan = false;
                }
            }
            if (isCan == true){

                user.setStatus(0); //设置账号状态==注销

                ResponseCode code = registerService.register(user);
                //注册成功后返回登录页面
                if(code.getCode() == ResponseCode.SUCCESS){
                    // TODO 可能会有错误
                    DialogShow.headInfo = null;
                    WindowParam.reSize(application.getStage(), WindowParam.Login_Height, WindowParam.Login_Width);
                    application.showView(loginView.getClass());
                }else {
                    //TODO 错误处理
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MoveController.move(application.getStage(), Register);
        quit();
        minimize();
        back();
        selectImage();
        bindRadioButton();
        register();
    }

    /**
     * 绑定RadioButton
     */
    private void bindRadioButton(){
        selectMale.setToggleGroup(group);
        selectFemale.setToggleGroup(group);

        selectMale.setUserData("男");
        selectFemale.setUserData("女");
        group.selectToggle(null);
    }


    private void showError(Label label){
        label.setText(label.getId() + "不能为空");
        label.setVisible(true);
    }
    private void hideError(Label label){
        label.setVisible(false);
    }
    private void hideError(){
        hideError(accountError);hideError(nameError);hideError(passwordError);
        hideError(rePasswordError);hideError(ageError);hideError(phoneError);
    }

    public Button getImage(){
        return image;
    }
}
