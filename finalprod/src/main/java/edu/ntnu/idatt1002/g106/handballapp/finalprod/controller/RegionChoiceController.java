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
        SwitchScene.setCurrentRegion("RegionSor");
        SwitchScene.switchScene("RegionSor", event);
    }

    public void toEasternRegion(ActionEvent event) throws IOException {
        SwitchScene.setCurrentRegion("RegionOst");
        SwitchScene.switchScene("RegionOst", event);
    }

    public void toNordernRegion(ActionEvent event) throws IOException {
        SwitchScene.setCurrentRegion("RegionNord");
        SwitchScene.switchScene("RegionNord", event);
    }

    public void toWesternRegion(ActionEvent event) throws IOException {
        SwitchScene.setCurrentRegion("RegionVest");
        SwitchScene.switchScene("RegionVest", event);
    }
}
