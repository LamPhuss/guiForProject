package com.example.demogui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class test2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String imagePath = "com/example/demogui/background.jpg";
        URL imageUrl = getClass().getClassLoader().getResource(imagePath);
        System.out.println(imageUrl);
        if (imageUrl != null) {
            Image image = new Image(imageUrl.toString());
            ImageView imageView = new ImageView(image);
            AnchorPane pane = new AnchorPane(imageView);
            Scene scene = new Scene(pane, 400, 300);
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            System.out.println("Không tìm thấy tập tin hình ảnh!");
            Label errorLabel = new Label("Không tìm thấy tập tin hình ảnh!");
            AnchorPane.setTopAnchor(errorLabel, 10.0);
            AnchorPane.setLeftAnchor(errorLabel, 10.0);
            AnchorPane root = new AnchorPane(errorLabel);
            Scene scene = new Scene(root, 400, 300);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}