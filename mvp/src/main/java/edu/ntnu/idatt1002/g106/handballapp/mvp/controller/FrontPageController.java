package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class FrontPageController implements Initializable{

    @FXML private Button newTournamentButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    public void handleButtonClick(){
        System.out.println("hei eirik");
        newTournamentButton.setText("Eirik Er stygg");
    }

}
