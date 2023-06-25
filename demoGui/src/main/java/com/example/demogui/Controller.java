package com.example.demogui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    ArrayList<String> words = new ArrayList<>(
            Arrays.asList("test", "dog","Human", "Days of our life", "The best day",
                    "Friends", "Animal", "Human", "Humans", "Bear", "Life",
                    "This is some text", "Words", "222", "Bird", "Dog", "A few words",
                    "Subscribe!", "SoftwareEngineeringStudent", "You got this!!",
                    "Super Human", "Super", "Like")
    );
    ArrayList<String> words1 = new ArrayList<>(
            Arrays.asList("test", "dog","Human", "Days of our life", "The best day",
                    "Friends", "Animal", "Human", "Humans", "Bear", "Life"
                    )
    );


    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle ) {
        listView.getItems().addAll(words);
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Tạo hyperlink và thiết lập nội dung
                    Hyperlink hyperlink = new Hyperlink(item.trim());
                    // Xử lý sự kiện click cho hyperlink
                    hyperlink.setOnAction(event -> {
                        // Tạo FXML loader để tải file FXML
                        try {
                            // Load FXML file
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("infoScene.fxml"));
                            Parent root = loader.load();
                            // Create new scene
                            Scene newScene = new Scene(root);
                            // Get controller for the new scene
                            infoSceneController controller = loader.getController();
                            // Set information for the controller
                            controller.setInfo(item);
                            // Get the current stage and set the new scene
                            Stage stage = (Stage) getScene().getWindow();
                            stage.setScene(newScene);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    // Thiết lập nội dung cho ListCell
                    setGraphic(hyperlink);
                }
            }
        });

    }
    @FXML
    void search(ActionEvent event) {
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(),words));
    }


    private List<String> searchList(String searchWords, List<String> listOfStrings) {
        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(""));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }
    @FXML
    public void filterByCharacter(ActionEvent event) {
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(),words));
    }
    @FXML
    public void filterByDynasty(ActionEvent event) {

    }
    @FXML
    public void filterByMonument(ActionEvent event) {

    }

}