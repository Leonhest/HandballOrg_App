package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.SwitchScene;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Tournament;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.TournamentRegister;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;


public class FrontPageController implements Initializable {


    @FXML private Button trondheimTournament;
    @FXML private Button askerTournament;
    @FXML private Button osloTournament;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void newTournament(ActionEvent actionEvent) throws IOException {

        SwitchScene.switchScene("SetUpTournament", actionEvent);
    }

    //TODO: Fix the linking between tournaments and the graphic front page. Link icon to a specific tournament!!!
    @FXML
    public void toMainPage(ActionEvent actionEvent) throws IOException {
        Button selectedButton = (Button) actionEvent.getSource();

        HandballApplication.setChosenTournament(HandballApplication.findID(selectedButton.getId()));
        //System.out.println("Tournament id " + HandballApplication.chosenTournament);

        SwitchScene.switchScene("MainPage", actionEvent);
    }
}
