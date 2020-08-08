package com.hbr.weChat.config;

import de.felixroske.jfxsupport.AbstractFxmlView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DialogShow {

    /**
     * 更换使用位置的时候，将stage置为null即可
     */
    public static Stage stage;

    public static String headInfo;


    public static Stage secondStage; //在个人信息时换头像的

    public static Stage show(AbstractFxmlView view){
        if(stage == null){
            stage = new Stage();
            stage.initStyle(DialogShow.stage.getStyle().TRANSPARENT);

        }
        if (stage.getScene() == null) {
            stage.setScene(new Scene(view.getView()));
        } else {
            stage.getScene().setRoot(view.getView());
        }
        stage.setScene(stage.getScene());
        return stage;
    }
}
