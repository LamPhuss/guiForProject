package com.example.demogui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {
    List<String> selectedList = new ArrayList<String>();
    List<String> characterList = Arrays.asList("A", "B", "C", "D", "E");
    List<String> dynastyList = Arrays.asList("F", "G", "H", "I", "K");
    List<String> monumentList = Arrays.asList("L", "ML", "Ndưa", "O", "P");
    List<String> festivalList = Arrays.asList("Q", "R", "S", "T", "V");

    @FXML
    private BorderPane mainBorder;
    @FXML
    private AnchorPane detailPane;
    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> listView;
    @FXML
    private Button characterButton;
    @FXML
    private Button monumentButton;
    @FXML
    private Button dynastyButton;
    @FXML
    private Button festivalButton;
    @FXML
    private Button backButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle ) {



    }
    @FXML
    void search(ActionEvent event) {

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            List<String> new_List = new ArrayList<>();
            new_List.addAll(searchList(newValue, selectedList));
            listView.getItems().clear();
            listView.getItems().addAll(new_List);

        });
    }


    private List<String> searchList(String searchWords, List<String> listOfStrings) {
        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(""));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }
    @FXML
    void showCharacterList(ActionEvent event) {
        // Tạo danh sách muốn hiển thị
        selectedList.clear();
        selectedList.addAll(characterList);
        setHyperLink();
        // Hiển thị danh sách lên ListView
        listView.getItems().setAll(characterList);
    }
    @FXML
    public void showDynastyList(ActionEvent event) {

        selectedList.clear();
        selectedList.addAll(dynastyList);
        setHyperLink();
        // Hiển thị danh sách lên ListView
        listView.getItems().setAll(dynastyList);
    }
    @FXML
    public void showMonumentList(ActionEvent event) {

        selectedList.clear();
        selectedList.addAll(monumentList);
        setHyperLink();
        // Hiển thị danh sách lên ListView
        listView.getItems().setAll(monumentList);
    }

    @FXML
    public void showFestivalList(ActionEvent event) {

        selectedList.clear();
        selectedList.addAll(festivalList);
        setHyperLink();
        // Hiển thị danh sách lên ListView
        listView.getItems().setAll(festivalList);
    }
    public void setHyperLink(){
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {

                    // Tạo hyperlink và thiết lập nội dung
                    Hyperlink hyperlink = new Hyperlink(item.trim());
                    // Xử lý sự kiện click cho hyperlink
                    hyperlink.setOnAction(event -> {
                        //listView.setVisible(false);
                        if (characterList.contains(item)) {
                            FxmlLoader object = new FxmlLoader();
                            Pane view = object.getPane("figureScene");
                            mainBorder.setCenter(view);
                            searchBar.setVisible(false);
                            backButton.setVisible(true);
                        }
                        if (dynastyList.contains(item)) {
                            FxmlLoader object = new FxmlLoader();
                            Pane view = object.getPane("governmentScene");
                            mainBorder.setCenter(view);
                            searchBar.setVisible(false);
                            backButton.setVisible(true);
                        }
                        if (monumentList.contains(item)) {
                            FxmlLoader object = new FxmlLoader();
                            Pane view = object.getPane("placeScene");
                            mainBorder.setCenter(view);
                            searchBar.setVisible(false);
                            backButton.setVisible(true);
                        }
                        if (festivalList.contains(item)) {
                            FxmlLoader object = new FxmlLoader();
                            Pane view = object.getPane("festivalScene");
                            mainBorder.setCenter(view);
                            searchBar.setVisible(false);
                            backButton.setVisible(true);
                        }
                    });
                    // Thiết lập nội dung cho ListCell
                    setGraphic(hyperlink);
                }
            }
        });
    }

    public void backAction(ActionEvent Event) {
        //listView.setVisible(true);
        searchBar.setVisible(true);
        mainBorder.setCenter(listView);
        backButton.setVisible(false);
    }

}