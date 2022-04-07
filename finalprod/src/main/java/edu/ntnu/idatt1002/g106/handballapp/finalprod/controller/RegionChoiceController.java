package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class will be the first page you see when program started
 * it will be used as a region selection, witch four buttons that will take the user to the different regions
 * @author Gruppe 6
 */
public class RegionChoiceController implements Initializable {

    /**
     * {@inheritDoc}
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void toRegionController(ActionEvent event) throws IOException {
        Button selected = (Button) event.getSource();
        HandballApplication.setChosenRegion(selected.getId());
        //TODO: FXML scenes should not be named Controllers!
        SwitchScene.switchScene("RegionController", event);
    }
}
