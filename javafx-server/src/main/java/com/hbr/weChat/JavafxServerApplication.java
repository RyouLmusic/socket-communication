package com.hbr.weChat;

import com.hbr.weChat.view.HomeView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.SplashScreen;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavafxServerApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launch(JavafxServerApplication.class, HomeView.class, new SplashScreen(){
            @Override
            public boolean visible() {
                return false;
            }
        },args);
    }
    @Override
    public void beforeInitialView(Stage stage, ConfigurableApplicationContext ctx) {
        super.beforeInitialView(stage, ctx);
        getStage().initStyle(getStage().getStyle().UNDECORATED);//设定窗口无边框
        getStage().xProperty().addListener((observable, oldValue, newValue) -> {

        });

    }

}
