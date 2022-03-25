package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.TournamentRegister;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Tournament;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.SwitchScene;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SetUpTournamentController implements Initializable {

    private Tournament tournament;

    @FXML
    private TextField tournamentNameTextFieldInput;
    @FXML
    private TextField tournamentPlaceTextFieldInput;
    @FXML
    private ChoiceBox<String> tournamentLayoutInput;
    @FXML
    private ChoiceBox<Integer> tournamentNumFieldsInput;
    @FXML
    private ChoiceBox<Integer> tournamentNumTeamsInput;
    @FXML
    private DatePicker tournamentStartDateInput;
    @FXML
    private DatePicker tournamentEndDateInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tournamentLayoutInput.getItems().add(0, "Layout 1");
        tournamentLayoutInput.getItems().add(1, "Layout 2");
        tournamentLayoutInput.setValue("Layout 1");

        for (int i = 0; i < 3; i++) {
            tournamentNumFieldsInput.getItems().add(i, i + 1);
        }

        tournamentNumFieldsInput.setValue(1);

        for (int i = 0; i < 7; i++) {
            tournamentNumTeamsInput.getItems().add(i, i + 4);
        }
        tournamentNumTeamsInput.setValue(4);

    }

        @FXML
        public void confirmTournament (ActionEvent actionEvent) throws IOException {
            String tournamentLayout = tournamentLayoutInput.getValue();
            String tournamentName = tournamentNameTextFieldInput.getText();
            String tournamentPlace = tournamentPlaceTextFieldInput.getText();
            int tournamentNumFields = tournamentNumFieldsInput.getValue();
            int tournamentNumTeams = tournamentNumTeamsInput.getValue();
            LocalDate tournamentStartDate = tournamentStartDateInput.getValue();
            LocalDate tournamentEndDate = tournamentEndDateInput.getValue();

            //TODO Add tournamentID check
            tournament = new Tournament(HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().size(), tournamentName, tournamentStartDate,
                    tournamentEndDate, tournamentLayout, tournamentPlace, tournamentNumFields, tournamentNumTeams);

            HandballApplication.adminList.get(0).getTournamentRegister().addTournament(tournament);
            HandballApplication.setChosenTournament(tournament.getTournamentID());

            //todo: add on front page

            SwitchScene.switchScene("MainPage", actionEvent);
        }

    @FXML
    private void logOutButton(){
        if (AlertBox.logOut()){
            Platform.exit();
        }
    }
}


//TODO: Exception handling for invalid tournament set up
