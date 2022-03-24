package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.SwitchScene;
import javafx.fxml.Initializable;
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
        SwitchScene.switchScene("SetUpTournament", actionEvent);
    }

    public void toMainPage(ActionEvent actionEvent) throws IOException {
        SwitchScene.switchScene("MainPage", actionEvent);
    }
}
