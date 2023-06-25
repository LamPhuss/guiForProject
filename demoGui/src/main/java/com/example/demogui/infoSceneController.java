package com.example.demogui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class infoSceneController implements Initializable {

    @FXML
    private Label label;

    private String info;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText(info);
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainScene.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Scene previousScene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

