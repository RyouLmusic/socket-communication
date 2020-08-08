package com.hbr.weChat;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        ObservableList<String> data = FXCollections.observableArrayList();

        ListView<String> listView = new ListView<String>(data);
        listView.setPrefSize(200, 250);

        data.addAll("A", "B", "C", "D", "E");

        listView.setItems(data);
        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val,
                 String new_val) -> {
                    System.out.println(new_val);

                });

        Button button = new Button("delete");
        button.setOnAction(event -> {
            listView.getItems().remove(0, data.size());
        });
        button.setPrefWidth(30.0);
        button.setPrefHeight(30.0);
        button.setLayoutX(0);
        button.setLayoutY(0);
        StackPane root = new StackPane();

        root.getChildren().add(listView);
        root.getChildren().add(button);
        primaryStage.setScene(new Scene(root, 200, 250));
        primaryStage.show();
    }
}