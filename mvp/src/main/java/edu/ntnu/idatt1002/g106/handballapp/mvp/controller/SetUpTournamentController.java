package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Tournament;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


import javax.swing.*;
import java.io.IOException;
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
    public void confirmTournament(ActionEvent actionEvent) throws IOException {
        String tournamentLayout = tournamentLayoutInput.getValue();
        String tournamentName = tournamentNameTextFieldInput.getText();
        String tournamentPlace = tournamentPlaceTextFieldInput.getText();
        int tournamentNumFields = tournamentNumFieldsInput.getValue();
        int tournamentNumTeams = tournamentNumTeamsInput.getValue();
        LocalDate tournamentStartDate = tournamentStartDateInput.getValue();
        LocalDate tournamentEndDate = tournamentEndDateInput.getValue();

        //TODO Add tournamentID check
        Tournament tournament = new Tournament(0, tournamentStartDate, tournamentEndDate, tournamentLayout, tournamentPlace, tournamentNumFields, tournamentNumTeams);
        HandballApplication.adminList.get(0).getTournamentRegister().addTournament(tournament);

        System.out.println("tournamentLayout " + tournamentLayout + "\n" + "tournamentName " + tournamentName + "\n" +
                "tournamentPlace " + tournamentPlace + "\n" + "tournamentNumFields " + tournamentNumFields + "\n" +
                "tournamentNumTeams " + tournamentNumTeams + "\n" + "tournamentStartDate " + tournamentStartDate +
                "tournamentEndDate " + tournamentEndDate + "\n" + "SLUTT");
        Parent viewMainPage = FXMLLoader.load(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/MainPage.fxml"));
        Scene mainPage = new Scene(viewMainPage);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(mainPage);
        window.show();

    }

}

//TODO: Exception handling for invalid tournament set up
