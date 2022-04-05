package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.Tournament;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SetUpTournamentController implements Initializable {

    private Tournament tournament = null;
    ArrayList<Tournament> observerList = new ArrayList<>();

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

    /**
     * {@inheritDoc}
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //observerList = new ArrayList<>();

        tournamentLayoutInput.getItems().add(0, "Layout 1");
        tournamentLayoutInput.getItems().add(1, "Layout 2");
        tournamentLayoutInput.setValue("Layout 1");

        for (int i = 0; i < 3; i++) {
            tournamentNumFieldsInput.getItems().add(i, i + 1);
        }

        tournamentNumFieldsInput.setValue(1);

        for (int i = 2; i < 6; i++) {
            tournamentNumTeamsInput.getItems().add(i-2, (int) Math.pow(2,i));
        }
        tournamentNumTeamsInput.setValue(4);

    }

    /**
     * method for confirming when a new tournament i to be created
     * @param actionEvent button event
     * @throws IOException
     */
    @FXML
    public void confirmTournament (ActionEvent actionEvent) throws IOException {
        //TODO Add tournamentID check
        boolean correctInformation = false;
        String tournamentLayout = tournamentLayoutInput.getValue();
        String tournamentName = tournamentNameTextFieldInput.getText();
        String tournamentPlace = tournamentPlaceTextFieldInput.getText();
        int tournamentNumFields = tournamentNumFieldsInput.getValue();
        int tournamentNumTeams = tournamentNumTeamsInput.getValue();
        LocalDate tournamentStartDate = tournamentStartDateInput.getValue();
        LocalDate tournamentEndDate = tournamentEndDateInput.getValue();
        try {
            tournament = new Tournament(HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().size(), tournamentName, tournamentStartDate,
                    tournamentEndDate, tournamentLayout, tournamentPlace, tournamentNumFields, tournamentNumTeams, SwitchScene.getCurrentRegion());
            correctInformation = true;
        } catch (NullPointerException e) {
            AlertBox.alertError("Remember to fill inn all information");
        } catch (IllegalArgumentException e) {
            AlertBox.alertError(e.getMessage());
        } catch (Exception e){
            AlertBox.alertError("System fail");
            return;
        }
        if (correctInformation) {
            HandballApplication.adminList.get(0).getTournamentRegister().addTournament(tournament);
            HandballApplication.setChosenTournament(tournament.getTournamentID());
            SwitchScene.switchScene("MainPage", actionEvent);
        }
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void toRegionChoice(ActionEvent event) throws IOException {
        SwitchScene.switchScene(SwitchScene.getCurrentRegion(), event);
    }

    /**
     * log out method
     */
    @FXML
    private void logOutButton(){
        if (AlertBox.logOut() == 1){
            System.exit(-1);
        }
    }
}


//TODO: Exception handling for invalid tournament set up
