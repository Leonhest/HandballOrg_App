package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;


public class FrontPageController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //hahaha you found the ester egg, this method is useless in this class in the MVP
    }

    public void newTournament(ActionEvent actionEvent) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/SetUpTournament.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //this line gets the stage information
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    //TODO: Fix the linking between tournaments and the graphic front page. Link icon to a specific tournament!!!
    public void toMainPage(ActionEvent actionEvent) throws IOException {
        HandballApplication.setChosenTournament(1);
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/MainPage.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //this line gets the stage information
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}
