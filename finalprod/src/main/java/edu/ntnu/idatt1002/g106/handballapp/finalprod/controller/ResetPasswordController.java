package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * this class will be used to reset a password in future development
 */
public class ResetPasswordController implements Initializable {

    /**
     * {@inheritDoc}
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * this takes the user to log in page
     * @param event any event
     * @throws IOException when path not found
     */
    public void toLoginPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("Login", event);
    }
}
