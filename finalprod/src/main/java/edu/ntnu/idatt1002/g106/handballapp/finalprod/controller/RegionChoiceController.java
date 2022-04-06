package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegionChoiceController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void toRegionController(ActionEvent event) throws IOException {
        Button selected = (Button) event.getSource();
        HandballApplication.setChosenRegion(selected.getId());
        SwitchScene.switchScene("RegionController", event);
    }
}
