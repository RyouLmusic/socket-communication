package com.hbr.weChat.controller;

import com.hbr.weChat.JavafxDemo0Application;
import com.hbr.weChat.config.DialogShow;
import com.hbr.weChat.config.WindowParam;
import com.hbr.weChat.dto.ResponseCode;
import com.hbr.weChat.model.User;
import com.hbr.weChat.service.LoginService;
import com.hbr.weChat.view.ForgetView;
import com.hbr.weChat.view.LoginView;
import com.hbr.weChat.view.MainWindowView;
import com.hbr.weChat.view.RegisterView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class LoginController implements Initializable {

    @FXML
    private Pane Login;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField Password;
    @FXML
    private Button loginButton;
    @FXML
    private Button register;
    @FXML
    private Button forgetPassword;

    @FXML
    private Button quit;
    @FXML
    private Button minimize;

    @Autowired
    JavafxDemo0Application application;
    @Autowired
    ForgetView forgetView;
    @Autowired
    LoginView loginView;
    @Autowired
    RegisterView registerView;
    @Autowired
    MainWindowView mainWindowView;

    /**
     * 服务
     */
    @Autowired
    LoginService loginService;

    private User ownInfo;
    private List<User> users;

    public void register(){
        register.setOnAction(event -> {
            WindowParam.reSize(application.getStage(), WindowParam.Register_Height, WindowParam.Register_Width);
            application.showView(registerView.getClass());
        });
    }

    private void forgetPassword(){

        forgetPassword.setOnAction(event -> {
            WindowParam.reSize(application.getStage(), WindowParam.Forget_Height, WindowParam.Forget_Width);
            application.showView(forgetView.getClass());
        });
    }

    private void quit() {
        quit.setOnAction(event -> {
            application.getStage().close();
            if (DialogShow.stage != null){
                DialogShow.stage.close();
            }
        });
    }

    private void minimize(){
        minimize.setOnAction(event -> application.getStage().setIconified(true));
    }
    private void login(){
        loginButton.setOnAction(event -> {
            String account = UserName.getText();
            String password = Password.getText();
            ResponseCode code = loginService.login(account, password);
            if(code.getCode() == ResponseCode.SUCCESS){
                //TODO 成功后的操作
                this.users  = code.getUsers();
                this.ownInfo = code.getOwnInfo();
                WindowParam.reSize(application.getStage(), WindowParam.Main_Window_Height, WindowParam.Main_Window_Width);
                application.showView(mainWindowView.getClass());

            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        register();
        forgetPassword();
        login();
        quit();
        minimize();
        MoveController.move(application.getStage(), Login);

    }

    public List<User> getUsers() {
        return users;
    }

    public User getOwnInfo() {
        return this.ownInfo;
    }
}
