package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegionChoiceController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void toSouthernRegion(ActionEvent event) throws IOException {
        SwitchScene.setCurrentRegion("SouthernRegion");
        SwitchScene.switchScene("SouthernRegion", event);
    }

    public void toEasternRegion(ActionEvent event) throws IOException {
        SwitchScene.setCurrentRegion("EasternRegion");
        SwitchScene.switchScene("EasternRegion", event);
    }

    public void toNorthernRegion(ActionEvent event) throws IOException {
        SwitchScene.setCurrentRegion("NorthernRegion");
        SwitchScene.switchScene("NorthernRegion", event);
    }

    public void toWesternRegion(ActionEvent event) throws IOException {
        SwitchScene.setCurrentRegion("WesternRegion");
        SwitchScene.switchScene("WesternRegion", event);
    }
}
