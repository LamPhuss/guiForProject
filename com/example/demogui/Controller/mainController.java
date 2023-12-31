package com.example.demogui.Controller;

import com.example.demogui.Entity.*;
import entity.Character;
import entity.Entity;
import com.example.demogui.FxmlLoader;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.*;

public class mainController implements Initializable {
    @FXML
    public TableColumn<Entity, String> nameCol;
    @FXML
    public TableColumn<Entity, String> desCol;
    @FXML
    private TableView<Entity> tableView;

    @FXML
    private BorderPane mainBorder;

    @FXML
    private TextField searchBar;

    @FXML
    private Button backButton;
    ObservableList<Entity> selectedList = FXCollections.observableArrayList();

    public ObservableList<entity.Character> createCharList() throws FileNotFoundException {
        JsonArray jsonArray = JsonParser.parseReader(new FileReader("C:\\Users\\LamPhuss\\Downloads\\demoGUI-master\\demoGUI-master\\data\\Character\\Người Kể Sử.json")).getAsJsonArray();
        ObservableList<entity.Character> characters = FXCollections.observableArrayList();
        for (JsonElement jsonElement : jsonArray) {
            if (jsonElement.isJsonNull()) {
                continue;
            }
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            entity.Character character = new entity.Character(jsonObject);
            characters.add(character);
        }
        return characters;
    }
    ObservableList<Place> monument = FXCollections.observableArrayList(

    );
    ObservableList<Festival> festival = FXCollections.observableArrayList(

    );
    ObservableList<Government> governments = FXCollections.observableArrayList(
    );
    ObservableList<Event> events = FXCollections.observableArrayList(

    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    void tableViewAction(){
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Kiểm tra xem người dùng đã nhấp đúp chuột hay chưa
                Entity selectedEntity = tableView.getSelectionModel().getSelectedItem();
                if (selectedEntity != null) {
                    try {
                        handleClickedEvent(selectedEntity);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }


    @FXML
    void search(ActionEvent event) {

        FilteredList<Entity> filteredList = new FilteredList<>(selectedList, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(entity -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyWord = newValue.toLowerCase();
                if (entity.getName().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;
                } else if (entity.getDescription().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Entity> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }

    @FXML
    void showCharacterList(ActionEvent event) throws FileNotFoundException {
        ObservableList<entity.Character> characters = createCharList();
        // Tạo danh sách muốn hiển thị
        selectedList.clear();
        selectedList.addAll(characters);
        nameCol.setCellValueFactory(new PropertyValueFactory<entity.Entity, String>("name"));
        desCol.setCellValueFactory(new PropertyValueFactory<entity.Entity, String>("description"));
        tableView.setItems(selectedList);

        tableViewAction();
        searchBar.setVisible(true);
        mainBorder.setCenter(tableView);
        backButton.setVisible(false);
        search(event);
    }
    /*
    @FXML
    public void showDynastyList(ActionEvent event) {
        selectedList.clear();
        selectedList.addAll(governments);
        nameCol.setCellValueFactory(new PropertyValueFactory<Entity, String>("name"));
        desCol.setCellValueFactory(new PropertyValueFactory<Entity, String>("description"));
        tableView.setItems(selectedList);
        tableViewAction();
        searchBar.setVisible(true);
        mainBorder.setCenter(tableView);
        backButton.setVisible(false);
        search(event);
    }

    @FXML
    public void showMonumentList(ActionEvent event) {
        selectedList.clear();
        selectedList.addAll(monument);
        // Hiển thị danh sách lên ListView
        nameCol.setCellValueFactory(new PropertyValueFactory<Entity, String>("name"));
        desCol.setCellValueFactory(new PropertyValueFactory<Entity, String>("description"));
        tableView.setItems(selectedList);
        tableViewAction();
        searchBar.setVisible(true);
        mainBorder.setCenter(tableView);
        backButton.setVisible(false);
        search(event);
    }

    @FXML
    public void showFestivalList(ActionEvent event) {
        selectedList.clear();
        selectedList.addAll(festival);
        // Hiển thị danh sách lên ListView
        nameCol.setCellValueFactory(new PropertyValueFactory<Entity, String>("name"));
        desCol.setCellValueFactory(new PropertyValueFactory<Entity, String>("description"));
        tableView.setItems(selectedList);
        tableViewAction();
        searchBar.setVisible(true);
        mainBorder.setCenter(tableView);
        backButton.setVisible(false);
        search(event);
    }

    @FXML
    public void showEventList(ActionEvent event) {
        selectedList.clear();
        selectedList.addAll(events);
        // Hiển thị danh sách lên ListView
        nameCol.setCellValueFactory(new PropertyValueFactory<Entity, String>("name"));
        desCol.setCellValueFactory(new PropertyValueFactory<Entity, String>("description"));
        tableView.setItems(selectedList);
        tableViewAction();
        searchBar.setVisible(true);
        mainBorder.setCenter(tableView);
        backButton.setVisible(false);
        search(event);
    }

     */


    public Hyperlink setHyperLink(String keyword ){
        Hyperlink itemToHyperlink = new Hyperlink(keyword);
        itemToHyperlink.setOnAction(event -> {
            //ObservableList<Entity> govCheck = FXCollections.observableArrayList(governments);
            ObservableList<Entity> chrCheck = null;
            try {
                chrCheck = FXCollections.observableArrayList(createCharList());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            /*
            ObservableList<Entity> monuCheck = FXCollections.observableArrayList(monument);
            ObservableList<Entity> fesCheck = FXCollections.observableArrayList(festival);
            ObservableList<Entity> evnCheck = FXCollections.observableArrayList(events);

             */
            Entity foundEntity = null;
            for (Entity entity : chrCheck) {
                if (entity.getName().equals(keyword)) {
                    foundEntity = entity;
                    break;
                }
            }
            /*
            for (Entity entity : govCheck) {
                if (entity.getName().equals(keyword)) {
                    foundEntity = entity;
                    break;
                }
            }
            for (Entity entity : monuCheck) {
                if (entity.getName().equals(keyword)) {
                    foundEntity = entity;
                    break;
                }
            }
            for (Entity entity : fesCheck) {
                if (entity.getName().equals(keyword)) {
                    foundEntity = entity;
                    break;
                }
            }
            for (Entity entity : evnCheck) {
                if (entity.getName().equals(keyword)) {
                    foundEntity = entity;
                    break;
                }
            }*/
            try {
                handleClickedEvent(foundEntity);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        return itemToHyperlink;
    }

    public void backAction(ActionEvent Event) {
        //listView.setVisible(true);
        searchBar.setVisible(true);
        mainBorder.setCenter(tableView);
        backButton.setVisible(false);
    }
    public boolean checkEntityInList(String name , ObservableList<Entity> checkList){
        return checkList.stream()
                .map(Entity::getName)
                .anyMatch(name::equals);
    }
    public void handleClickedEvent(Entity handleEntity) throws FileNotFoundException {
        //ObservableList<Entity> govCheck = FXCollections.observableArrayList(governments);
        ObservableList<Entity> chrCheck = FXCollections.observableArrayList(createCharList());
        /*
        ObservableList<Entity> monuCheck = FXCollections.observableArrayList(monument);
        ObservableList<Entity> fesCheck = FXCollections.observableArrayList(festival);
        ObservableList<Entity> evnCheck = FXCollections.observableArrayList(events);*/
        String checkedName = handleEntity.getName();
        if (checkEntityInList(checkedName,chrCheck)) {
            FxmlLoader object = new FxmlLoader();
            ScrollPane view = object.getPane("characterScene", handleEntity);
            Character handleCharacter = (Character) handleEntity;
            AnchorPane anchorPane = (AnchorPane) view.getContent();
            Label chrRelLabel = (Label) anchorPane.lookup("#chrRel");
            List<String> charRel = new ArrayList<>(getStringList(handleCharacter.getRelatives()));
            if (charRel.size() == 0 ) {
                chrRelLabel.setText("Không có");
            } else {
                setHyperLinkForList(chrRelLabel, charRel);
            }
            mainBorder.setCenter(view);
            searchBar.setVisible(false);
            backButton.setVisible(true);
        }
/*
        if (checkEntityInList(checkedName, govCheck)) {
            FxmlLoader object = new FxmlLoader();
            Pane view = object.getPane("governmentScene",handleEntity);
            mainBorder.setCenter(view);
            searchBar.setVisible(false);
            backButton.setVisible(true);
            Government handleGovernment = (Government) handleEntity;
            Label gFigLabel = (Label) view.lookup("#gFigure");
            setHyperLinkForList(gFigLabel ,handleGovernment.getGovFigures());
            Label gFes = (Label) view.lookup("#gFes");
            setHyperLinkForList(gFes ,handleGovernment.getGovFestivals());
            Label gPlace = (Label) view.lookup("#gPlace");
            setHyperLinkForList(gPlace ,handleGovernment.getGovPlaces());
            Label gEvent = (Label) view.lookup("#gEvent");
            setHyperLinkForList(gEvent ,handleGovernment.getGovEvents());
        }

        if (checkEntityInList(checkedName,monuCheck)) {
            FxmlLoader object = new FxmlLoader();
            Pane view = object.getPane("placeScene",handleEntity);
            mainBorder.setCenter(view);
            searchBar.setVisible(false);
            backButton.setVisible(true);
            Place handlePlace = (Place) handleEntity;
            Label pFigLabel = (Label) view.lookup("#pFigure");
            setHyperLinkForList(pFigLabel ,handlePlace.getPlFigures());
            Label pFestivalLabel = (Label) view.lookup("#pFestival");
            setHyperLinkForList(pFestivalLabel ,handlePlace.getPlFestivals());
            Label pGovernmentsLabel = (Label) view.lookup("#pGovernments");
            setHyperLinkForList(pGovernmentsLabel ,handlePlace.getPlGovernments());
            Label pEventLabel = (Label) view.lookup("#pEvent");
            setHyperLinkForList(pEventLabel ,handlePlace.getPlEvents());
        }
        if (checkEntityInList(checkedName,fesCheck)) {
            FxmlLoader object = new FxmlLoader();
            Pane view = object.getPane("festivalScene",handleEntity);
            mainBorder.setCenter(view);
            searchBar.setVisible(false);
            backButton.setVisible(true);
            Festival handleFestival = (Festival) handleEntity;
            Label fFigLabel = (Label) view.lookup("#fFig");
            setHyperLinkForList(fFigLabel ,handleFestival.getfFigures());
            Label fPlaceLabel = (Label) view.lookup("#fPlace");
            setHyperLinkForList(fPlaceLabel ,handleFestival.getfPlaces());
            Label fGovLabel = (Label) view.lookup("#fGov");
            setHyperLinkForList(fGovLabel ,handleFestival.getfGovernments());
            Label fEventLabel = (Label) view.lookup("#fEvent");
            setHyperLinkForList(fEventLabel ,handleFestival.getfEvents());
        }
        if (checkEntityInList(checkedName,evnCheck)) {
            FxmlLoader object = new FxmlLoader();
            Pane view = object.getPane("eventScene",handleEntity);
            mainBorder.setCenter(view);
            searchBar.setVisible(false);
            backButton.setVisible(true);
            Event handleEvent = (Event) handleEntity;
            Label fPlaceLabel = (Label) view.lookup("#ePlace");
            setHyperLinkForList(fPlaceLabel ,handleEvent.getEvPlace());
        }*/
    }
    public void setHyperLinkForList(Label handleLabel ,List<String> handleList){
        TextFlow textFlow = new TextFlow();
        for (int i = 0; i < handleList.size(); i++) {
            String keyword = handleList.get(i);
            Hyperlink hyperlink = setHyperLink(keyword);
            hyperlink.setFont(new Font(14));
            if (i > 0) {
                textFlow.getChildren().add(new Text("; "));
            }
            textFlow.getChildren().add(hyperlink);
        }
        handleLabel.setWrapText(true);
        handleLabel.setGraphic(textFlow);
    }
    @FXML
    public void showMenu(ActionEvent actionEvent) {
        Character clone = new Character(null);
        FxmlLoader object = new FxmlLoader();
        /*Pane view = object.getPane("menuScene",clone);
        mainBorder.setCenter(view);*/
        searchBar.setVisible(false);

    }
    public List<String> getStringList(String handleString) throws FileNotFoundException {
        ObservableList<Character> charList = createCharList();
        List<String> stringList = new ArrayList<>();
        for (Character character : charList){
            if (handleString.contains(character.getName())){
                stringList.add(character.getName());
            }
        }
        return stringList;
    }
}