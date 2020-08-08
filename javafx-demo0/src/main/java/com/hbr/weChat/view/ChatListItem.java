package com.hbr.weChat.view;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class ChatListItem {

    private Pane pane;
    private Button head;
    private TextArea text;
    private Pane left;
    private Pane right;
    private Button arrow;

    public ChatListItem() {
        pane = new Pane();
        head = new Button();
        text = new TextArea();
        pane.setPrefSize(730, 150);
        left = new Pane();
        right = new Pane();
        arrow = new Button();
        arrow.setDisable(false);
        arrow.setPrefSize(32, 32);
        left.setPrefSize(580, 70);
        right.setPrefSize(580, 70);
        head.getStyleClass().add("head");
        pane.getStyleClass().add("pane");
        left.getStyleClass().add("pane");
        right.getStyleClass().add("pane");
        head.setPrefSize(50, 50);
        text.setPrefSize(480, 50);
        text.setWrapText(true);
        text.setEditable(false);
    }

        public Pane Left(String ihead,String itext,double width,double hight){//别人的消息
        text.getStyleClass().add("lefttext");
        arrow.getStyleClass().add("leftarrow");
        pane.setPrefHeight(110+hight);
        left.setPrefHeight(30+hight);
        head.setLayoutY(10);
        head.setLayoutX(10);
        text.setPrefSize(width,hight);
        text.setLayoutX(100);
        text.setLayoutY(30);
        arrow.setLayoutY(40);
        arrow.setLayoutX(85);
        text.setText(itext);
        setHeadPortrait(head,ihead);
        left.getChildren().add(head);
        left.getChildren().add(text);
        left.getChildren().add(arrow);
        pane.getChildren().add(left);

        return pane;


    }
    public Pane Right(String ihead, String itext, double width, double height) {//自己的消息
        text.getStyleClass().add("righttext");
        arrow.getStyleClass().add("rightarrow");
        head.setLayoutY(10);
        head.setLayoutX(510);
        pane.setPrefHeight(110 + height);
        right.setPrefHeight(30 + height);
        text.setPrefSize(width, height);
        //text=480 0 text = 470 10 460 20 480-width
        text.setLayoutY(30);
        text.setLayoutX(480 - width);
        arrow.setLayoutY(40);
        arrow.setLayoutX(475);
        text.setText(itext);
        setHeadPortrait(head, ihead);
        right.getChildren().add(head);
        right.getChildren().add(text);
        right.getChildren().add(left);
        right.getChildren().add(arrow);
        right.setLayoutX(150);
        pane.getChildren().add(right);
        return pane;
    }



    public void setHeadPortrait(Button button, String head){
        button.setStyle(String.format("-fx-background-image: url('/css/Image/head/%s.jpg')",head));
    }
}
