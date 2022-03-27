package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.SwitchScene;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Tournament;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.TournamentRegister;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.List;

import javafx.event.ActionEvent;


public class FrontPageController implements Initializable {

    @FXML HBox HBoxContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //hahaha you found the ester egg, this method is useless in this class in the MVP
        List<Tournament> tournaments = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments();
        if (tournaments.size() > 3) {
            for (int i = 3; i < tournaments.size(); i++) {
                createButtonForTournament(tournaments.get(i).getTournamentName());
                //System.out.println("New tournament name " + tournaments.get(i).getTournamentName());
            }
        }
    }

    @FXML
    public void newTournament(ActionEvent actionEvent) throws IOException {
        SwitchScene.switchScene("SetUpTournament", actionEvent);
    }

    @FXML
    public void createButtonForTournament(String tournamentName) {
        Button button = new Button(tournamentName);
        button.setId(tournamentName);
        button.setOnAction(event -> {
            try {
                toMainPage(event);
            } catch (IOException e) {//todo: Handle properly
                e.printStackTrace();
            }
        });
        VBox vBox = new VBox();
        vBox.getChildren().add(button);
        HBoxContainer.getChildren().add(vBox);
    }

    //TODO: Fix the linking between tournaments and the graphic front page. Link icon to a specific tournament!!!
    @FXML
    public void toMainPage(ActionEvent actionEvent) throws IOException {
        Button selectedButton = (Button) actionEvent.getSource();

        HandballApplication.setChosenTournament(HandballApplication.findID(selectedButton.getId()));

        SwitchScene.switchScene("MainPage", actionEvent);
    }
}
