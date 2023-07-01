package com.example.demogui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String imagePath = "com/example/demogui/background.jpg";
        Image image = new Image(imagePath);

        image.errorProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                System.out.println("Không thể tải tập tin hình ảnh!");
                Label errorLabel = new Label("Không thể tải tập tin hình ảnh!");
                AnchorPane.setTopAnchor(errorLabel, 10.0);
                AnchorPane.setLeftAnchor(errorLabel, 10.0);
                AnchorPane root = new AnchorPane(errorLabel);
                Scene scene = new Scene(root, 400, 300);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

        AnchorPane pane = new AnchorPane();
        pane.setStyle("-fx-background-image: url('" + imagePath + "');");
        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
