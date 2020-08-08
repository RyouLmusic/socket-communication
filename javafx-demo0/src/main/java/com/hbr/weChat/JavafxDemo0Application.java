package com.hbr.weChat;

import com.hbr.weChat.config.DialogShow;
import com.hbr.weChat.view.LoginView;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.SplashScreen;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavafxDemo0Application extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {

        launch(JavafxDemo0Application.class, LoginView.class, new SplashScreen(){
            public boolean visible(){
                return false;
            }
        }, args);
    }

    @Override
    public void beforeInitialView(Stage stage, ConfigurableApplicationContext ctx) {
        super.beforeInitialView(stage, ctx);
        getStage().initStyle(getStage().getStyle().UNDECORATED);//设定窗口无边框
        getStage().xProperty().addListener((observable, oldValue, newValue) -> {

        });

        //设置最小化
        getStage().setIconified(false);
        //设置最大化，不是全屏
        getStage().setMaximized(false);

    }
}
