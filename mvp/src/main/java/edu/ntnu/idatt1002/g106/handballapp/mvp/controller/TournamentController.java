package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TournamentController implements Initializable {
    //Textfield for TournamentName where user sets the name of the tournament
    @FXML private TextField TournamentNameTextFieldInput;
    //Textfield for TournamentPlace where user sets the place of the tournament
    @FXML private TextField TournamentPlaceTextFieldInput;
    //ChoiceBox for TournamentLayout where user picks layout of the tournament
    @FXML private ChoiceBox<String> TournamentLayoutInput;
    //ChoiceBox for TournamentNumFields where user picks number fields in the tournament
    @FXML private ChoiceBox<Integer> TournamentNumFieldsInput;
    //ChoiceBox for TournamentNumTeams where user picks number of teams in the tournament
    @FXML private ChoiceBox<Integer> TournamentNumTeamsInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        TournamentLayoutInput.getItems().add(0,"Layout 1");
        TournamentLayoutInput.getItems().add(1,"Layout 2");
        TournamentLayoutInput.setValue("Layout 1");

        TournamentNumFieldsInput.getItems().add(0, 1);
        TournamentNumFieldsInput.getItems().add(1, 2);
        TournamentNumFieldsInput.getItems().add(2, 3);
        TournamentNumFieldsInput.setValue(1);

        TournamentNumTeamsInput.getItems().add(0,4);
        TournamentNumTeamsInput.getItems().add(1,5);
        TournamentNumTeamsInput.getItems().add(2,6);
        TournamentNumTeamsInput.getItems().add(3,7);
        TournamentNumTeamsInput.getItems().add(4,8);
        TournamentNumTeamsInput.getItems().add(5,9);
        TournamentNumTeamsInput.getItems().add(6,10);
        TournamentNumTeamsInput.setValue(4);

        //TournamentNameTextFieldInput
    }

    public void TournamentLayoutInput(){

    }

    public void TournamentNumFieldsInput(){

    }

    public void TournamentNumTeamsInput(){

    }

    //public void TournamentName(ActionEvent )

    public void Confirm(ActionEvent actionEvent) {
        String TournamentLayout = TournamentLayoutInput.getValue();

    }
}
