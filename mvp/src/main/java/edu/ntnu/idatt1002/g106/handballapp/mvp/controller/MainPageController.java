package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    //TODO: take code from Trym and use them to make list of the different teams, and make phone-number as REGEX
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //TODO: make button log close program
    public void logout(){
        AlertBox.logOut();
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
