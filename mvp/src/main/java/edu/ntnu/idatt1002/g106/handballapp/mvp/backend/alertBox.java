package edu.ntnu.idatt1002.g106.handballapp.mvp.backend;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import java.net.URL;
import java.util.ResourceBundle;

public class alertBox implements Initializable{

    // create a alert
    Alert a = new Alert(Alert.AlertType.NONE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void logout() {
        // set alert type
        a.setAlertType(Alert.AlertType.CONFIRMATION);

        // set content text
        a.setContentText("Press button to ....");

        //show the dialog
        a.show();
    }
}

