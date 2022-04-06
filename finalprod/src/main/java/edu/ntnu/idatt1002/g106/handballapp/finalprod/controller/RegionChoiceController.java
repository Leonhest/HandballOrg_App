package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

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

    /**
     * this method takes the user to a specific region
     * @param event any event
     * @throws IOException when path not found
     */
    public void toSouthernRegion(ActionEvent event) throws IOException {
        SwitchScene.setCurrentRegion("SouthernRegion");
        SwitchScene.switchScene("SouthernRegion", event);
    }

    /**
     * this method takes the user to a specific region
     * @param event any event
     * @throws IOException when path not found
     */
    public void toEasternRegion(ActionEvent event) throws IOException {
        SwitchScene.setCurrentRegion("EasternRegion");
        SwitchScene.switchScene("EasternRegion", event);
    }

    /**
     * this method takes the user to a specific region
     * @param event any event
     * @throws IOException when path not found
     */
    public void toNorthernRegion(ActionEvent event) throws IOException {
        SwitchScene.setCurrentRegion("NorthernRegion");
        SwitchScene.switchScene("NorthernRegion", event);
    }

    /**
     * this method takes the user to a specific region
     * @param event any event
     * @throws IOException when path not found
     */
    public void toWesternRegion(ActionEvent event) throws IOException {
        SwitchScene.setCurrentRegion("WesternRegion");
        SwitchScene.switchScene("WesternRegion", event);
    }
}
