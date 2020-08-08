package com.hbr.weChat;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
public class Test extends Application{


        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {

            ObservableList<String> data = FXCollections.observableArrayList();

            ListView<String> listView = new ListView<String>(data);
            listView.setPrefSize(200, 250);
            listView.setEditable(true);

            data.addAll("A", "B", "C", "D", "E");
            data.add("ddd");
//            listView.setItems(data);
//            listView.setCellFactory((ListView<String> l) -> new ColorRectCell());
            StackPane root = new StackPane();
            root.getChildren().add(listView);
            primaryStage.setScene(new Scene(root, 200, 250));
            primaryStage.show();
        }

        static class ColorRectCell extends ListCell<String> {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                Rectangle rect = new Rectangle(100, 20);
                if (item != null) {
                    rect.setFill(Color.RED);
                    setGraphic(rect);
                }
            }
        }


        //Dialog窗口
        public void log(){
            //            Dialog dialog = new Dialog();
            DialogPane pane = new DialogPane();
//            pane.setContent(headPortraitView.getView());
//            dialog.initStyle(StageStyle.TRANSPARENT);
//            dialog.setDialogPane(pane);
//            dialog.show();

            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setDialogPane(pane);
            alert.show();
        }

}
