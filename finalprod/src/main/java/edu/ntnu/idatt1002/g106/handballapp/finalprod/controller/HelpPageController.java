package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelpPageController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void logoutMethod() {
        if (AlertBox.logOut() == 1){
            System.exit(-1);
        }
    }

    @FXML
    public void toMainPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("MainPage", event);
    }
}
