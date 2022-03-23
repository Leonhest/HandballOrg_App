package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private Button logoutButton;

    @FXML
    private AnchorPane scenePane;
    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void logout(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save before exiting");

        if (alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("You successfully logged out!");
            stage.close();
        }
    }
}