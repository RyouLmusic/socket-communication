package com.hbr.weChat.controller;

import javafx.scene.Parent;
import javafx.stage.Stage;

public class MoveController extends Stage {


    private static double xOffset = 0;
    private static double yOffset = 0;


    public static void move(Stage primaryStage, Parent root){
        /*
         * 鼠标按下时，记下相对于 root左上角(0,0) 的 x, y坐标, 也就是x偏移量 = x - 0, y偏移量 = y - 0
         */
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        /*
         * 根据偏移量设置primaryStage新的位置
         */
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    }

}
