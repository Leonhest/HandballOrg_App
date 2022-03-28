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

    /**
     * {@inheritDoc}
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Tournament> tournaments = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments();
        if (tournaments.size() > 3) {
            for (int i = 3; i < tournaments.size(); i++) {
                createButtonForTournament(tournaments.get(i).getTournamentName());
            }
        }
    }

    /**
     * method that sends program to specific screen
     * @param actionEvent button event
     * @throws IOException when path not found
     */
    @FXML
    public void newTournament(ActionEvent actionEvent) throws IOException {
        SwitchScene.switchScene("SetUpTournament", actionEvent);
    }

    /**
     * method that created a new button when a new tournament is created
     * @param tournamentName name of the tournament
     */
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
    /**
     * method that sends program to specific screen
     * @param actionEvent button event
     * @throws IOException when path not found
     */
    @FXML
    public void toMainPage(ActionEvent actionEvent) throws IOException {
        Button selectedButton = (Button) actionEvent.getSource();

        HandballApplication.setChosenTournament(HandballApplication.findID(selectedButton.getId()));

        SwitchScene.switchScene("MainPage", actionEvent);
    }
}
