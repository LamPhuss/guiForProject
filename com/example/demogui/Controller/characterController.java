package com.example.demogui.Controller;

import entity.Character;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.Desktop;

public class characterController {
    @FXML
    public Label chrName;
    @FXML
    public Label chrDOB;
    @FXML
    public Label chrRel;
    @FXML
    public Label chrDes;
    @FXML
    public Label chrChild;
    @FXML
    public Label chrWife;
    @FXML
    public Label chrDad;
    @FXML
    public Label chrMom;
    @FXML
    public Label chrLocation;
    @FXML
    public Label chrPos;
    @FXML
    public Label chrSource;
    @FXML
    public ImageView chrImage;


    public void setLabel(Character character) throws FileNotFoundException {
        chrName.setText(character.getName());
        chrDes.setText(character.getDescription());
        chrDOB.setText(character.getDob());
        if (character.getLocation() == null) {
            chrLocation.setText("Không rõ");
        } else {
            chrLocation.setText(character.getLocation());
        }
        if (character.getPosition() == null) {
            chrPos.setText("Không rõ");
        } else {
            chrPos.setText(character.getPosition());
        }
        System.out.println(character.getImage());
        if (character.getSource() == null) {
            chrSource.setText("Không rõ");
        } else {
            Hyperlink hyperlink = new Hyperlink(character.getSource());
            hyperlink.setOnAction(e -> {
                try {
                    Desktop.getDesktop().browse(new URI(character.getSource()));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            });
            hyperlink.setFont(new Font(14));
            chrSource.setGraphic(hyperlink);
            ;
        }

        if (character.getPartner() == null) {
            chrWife.setText("Không rõ");
        } else {
            chrWife.setText(character.getPartner());
        }
        if (character.getMom() == null) {
            chrMom.setText("Không rõ");
        } else {
            chrMom.setText(character.getMom());
        }
        if (character.getDad() == null) {
            chrDad.setText("Không rõ");
        } else {
            chrDad.setText(character.getDad());
        }
        if (character.getChildren() == null) {
            chrChild.setText("Không rõ");
        } else {
            chrChild.setText(character.getChildren());
        }
        Image image2 = new Image("C:\\Users\\LamPhuss\\Downloads\\demoGUI-master\\demoGUI-master\\"+character.getImage());
        //System.out.println(image2);
        chrImage.setImage(image2); // đặt hình ảnh mới

    }
}
