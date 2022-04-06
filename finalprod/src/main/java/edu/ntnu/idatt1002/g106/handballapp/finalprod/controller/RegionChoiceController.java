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


    //todo: remove duplicate...

    public void toSouthernRegion(ActionEvent event) throws IOException {
        HandballApplication.setChosenRegion("SouthernRegion");
        SwitchScene.switchScene("RegionController", event);
    }

    public void toEasternRegion(ActionEvent event) throws IOException {
        HandballApplication.setChosenRegion("EasternRegion");
        SwitchScene.switchScene("RegionController", event);
    }

    public void toNorthernRegion(ActionEvent event) throws IOException {
        HandballApplication.setChosenRegion("NorthernRegion");
        SwitchScene.switchScene("RegionController", event);
    }

    public void toWesternRegion(ActionEvent event) throws IOException {
        HandballApplication.setChosenRegion("WesternRegion");
        SwitchScene.switchScene("RegionController", event);
    }
}
