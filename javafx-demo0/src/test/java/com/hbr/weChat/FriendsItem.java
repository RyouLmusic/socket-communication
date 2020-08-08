package com.hbr.weChat;

import com.hbr.weChat.view.FriendsListItem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class FriendsItem extends Application {




    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ObservableList<Pane> data = FXCollections.observableArrayList();

        ListView<Pane> listView = new ListView<>(data);
        listView.setPrefSize(200, 250);
        Pane pane = new FriendsListItem("head10", "TIM", "1223", 1);
        Pane pane1 = new FriendsListItem("head1", "CAKE", "1223", 1);
        Pane pane2 = new FriendsListItem("head2", "US", "1223", 1);
        Pane pane3 = new FriendsListItem("head3", "CN", "1223", 1);
        Pane pane4 = new FriendsListItem("head4", "JP", "1223", 0);
        Pane pane5 = new FriendsListItem("head10", "TIM", "1223", 0);
        Pane pane6 = new FriendsListItem("head1", "CAKE", "1223",1 );
        Pane pane7 = new FriendsListItem("head2", "US", "1223", 0);
        Pane pane8 = new FriendsListItem("head3", "CN", "1223", 0);
        Pane pane9 = new FriendsListItem("head4", "JP", "1223", 1);
        Pane pane10 = new FriendsListItem("head10", "TIM", "1223", 11);
        Pane pane11 = new FriendsListItem("head1", "CAKE", "1223", 0);
        Pane pane12 = new FriendsListItem("head2", "US", "1223", 0);
        Pane pane13 = new FriendsListItem("head3", "CN", "1223", 1);
        Pane pane14 = new FriendsListItem("head4", "JP", "1223", 0);
        Pane pane15 = new FriendsListItem("head10", "TIM", "1223", 1);
        Pane pane16 = new FriendsListItem("head1", "CAKE", "1223", 0);
        Pane pane17 = new FriendsListItem("head2", "US", "1223", 1);
        Pane pane18 = new FriendsListItem("head3", "CN", "1223", 0);
        Pane pane19 = new FriendsListItem("head4", "JP", "1223", 0);
//        Label label = new Label("pane");
//        pane.getChildren().add(label);
        data.addAll(pane, pane1, pane2, pane3, pane4, pane5, pane6, pane7, pane8, pane9, pane10, pane11, pane12, pane13, pane14, pane15, pane16, pane17, pane18, pane19);

        listView.setItems(data);
//        listView.getSelectionModel().selectedItemProperty().addListener();
        StackPane root = new StackPane();
        root.getChildren().add(listView);
        primaryStage.setScene(new Scene(root, 600, 700));
        primaryStage.show();
    }

}
