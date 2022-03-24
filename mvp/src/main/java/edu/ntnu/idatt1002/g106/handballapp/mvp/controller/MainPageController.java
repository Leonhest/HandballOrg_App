package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.xml.stream.Location;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class MainPageController implements Initializable {


    @FXML private Button SetUpMatches;
    @FXML private Button FrontPage;
    @FXML private Button CupList;
    @FXML private Button RegisterResult;


    @FXML private AnchorPane scenePane;
    Stage stage;

    //TODO: take code from Trym and use them to make list of the different teams, and make phone-number as REGEX
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //TODO: make button log close program
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

    @FXML
    public void sendToFrontPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("FrontPage", event);
    }

    @FXML
    public void sendToSetUpMatches(ActionEvent event) throws IOException {
        SwitchScene.switchScene("SetUpMatches", event);
    }

    @FXML
    public void sendToRegisterTeam(ActionEvent event) throws IOException {
        SwitchScene.switchScene("CupList", event);
    }

    @FXML
    public void sendToRegisterResult(ActionEvent event) throws IOException {
        SwitchScene.switchScene("RegisterResult", event);
    }

    @FXML
    public void SelectDate(ActionEvent event) {

    }
}
