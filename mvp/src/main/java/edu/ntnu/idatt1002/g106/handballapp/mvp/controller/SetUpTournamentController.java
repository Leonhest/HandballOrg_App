package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SetUpTournamentController implements Initializable {
    //Textfield for TournamentName where user sets the name of the tournament
    @FXML private TextField tournamentNameTextFieldInput;
    //Textfield for TournamentPlace where user sets the place of the tournament
    @FXML private TextField tournamentPlaceTextFieldInput;
    //ChoiceBox for TournamentLayout where user picks layout of the tournament
    @FXML private ChoiceBox<String> tournamentLayoutInput;
    //ChoiceBox for TournamentNumFields where user picks number fields in the tournament
    @FXML private ChoiceBox<Integer> tournamentNumFieldsInput;
    //ChoiceBox for TournamentNumTeams where user picks number of teams in the tournament
    @FXML private ChoiceBox<Integer> tournamentNumTeamsInput;
    //DatePicker for tournamentEndDateInput where user picks end date for the tournament
    @FXML private DatePicker tournamentStartDateInput;
    //DatePicker for tournamentEndDateInput where user picks end date for the tournament
    @FXML private DatePicker tournamentEndDateInput;
    //Button for tournamentConfirm to confirm the layout of the tournament
    @FXML private Button tournamentConfirm;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        tournamentLayoutInput.getItems().add(0,"Layout 1");
        tournamentLayoutInput.getItems().add(1,"Layout 2");
        tournamentLayoutInput.setValue("Layout 1");

        tournamentNumFieldsInput.getItems().add(0, 1);
        tournamentNumFieldsInput.getItems().add(1, 2);
        tournamentNumFieldsInput.getItems().add(2, 3);
        tournamentNumFieldsInput.setValue(1);

        tournamentNumTeamsInput.getItems().add(0,4);
        tournamentNumTeamsInput.getItems().add(1,5);
        tournamentNumTeamsInput.getItems().add(2,6);
        tournamentNumTeamsInput.getItems().add(3,7);
        tournamentNumTeamsInput.getItems().add(4,8);
        tournamentNumTeamsInput.getItems().add(5,9);
        tournamentNumTeamsInput.getItems().add(6,10);
        tournamentNumTeamsInput.setValue(4);

    }

    @FXML
    public void confirmTournament() {
        String tournamentLayout = tournamentLayoutInput.getValue();
        String tournamentName = tournamentNameTextFieldInput.getText();
        String tournamentPlace = tournamentPlaceTextFieldInput.getText();
        int tournamentNumFields = tournamentNumFieldsInput.getValue();
        int tournamentNumTeams = tournamentNumTeamsInput.getValue();
        LocalDate tournamentStartDate = tournamentStartDateInput.getValue();
        LocalDate tournamentEndDate = tournamentEndDateInput.getValue();
        System.out.println("tournamentLayout " + tournamentLayout + "\n" + "tournamentName " + tournamentName + "\n" +
                "tournamentPlace " + tournamentPlace + "\n" + "tournamentNumFields " + tournamentNumFields + "\n" +
                "tournamentNumTeams " + tournamentNumTeams + "\n" + "tournamentStartDate " + tournamentStartDate +
                "tournamentEndDate " + tournamentEndDate + "\n" + "SLUTT");



    }
}
