package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResetPasswordController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void toLoginPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("Login", event);
    }
}
