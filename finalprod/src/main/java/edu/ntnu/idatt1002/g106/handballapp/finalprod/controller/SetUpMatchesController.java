package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class handles events when a match is to be created
 * @author Gruppe 6
 */
public class SetUpMatchesController implements Initializable {

    List<Match> matchList = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList();
    TeamRegister tournamentTeamRegister = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getTeamRegister();
    private ArrayList<String> referees = new ArrayList<>();

    Pattern hourMinPat = Pattern.compile("^[0-1]\\d:[0-5]\\d${5}");
    //TODO: Fix regex so there can't be 69 minutes

    @FXML
    private TableColumn<?, ?> friColumn;

    @FXML
    private TableColumn<?, ?> mondayColumn;

    @FXML
    private TableColumn<?, ?> satColumn;

    @FXML
    private TableColumn<?, ?> sunColumn;

    @FXML
    private TableColumn<?, ?> thuColumn;

    @FXML
    private TableColumn<LocalTime, String> timeColumn;

    @FXML
    private TableColumn<?, ?> tuesdayColumn;

    @FXML
    private TableColumn<?, ?> wedColumn;

    @FXML
    private ChoiceBox<Integer> fieldChoice;

    @FXML
    private ChoiceBox<String> refereeChoice;

    @FXML
    private ChoiceBox<String> refereeChoice2;

    @FXML
    private ChoiceBox<String> teamChoice1;

    @FXML
    private ChoiceBox<String> teamChoice2;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField startTime;

    @FXML
    private Text errorMessage;

    /**
     * {@inheritDoc}
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Choice box for the fields
        for(int i = 0; i < 5; i++){
            fieldChoice.getItems().add(i,i+1);
        }
        fieldChoice.setValue(1);

        //Connecting a database to here wouldn't be too bad
        for(Team team : tournamentTeamRegister.getListTeams()){
            teamChoice1.getItems().add(team.getTeamName());
            teamChoice2.getItems().add(team.getTeamName());
        }

        teamChoice1.setValue("Team 1");
        teamChoice2.setValue("Team 2");

        refereeChoice.getItems().add("Eirik Dommerstad");
        refereeChoice.getItems().add("Hans Magne Asheim");
        refereeChoice.getItems().add("Leon Hesthaug");
        refereeChoice.setValue("Referee");

        refereeChoice2.getItems().add("Eirik Dommerstad");
        refereeChoice2.getItems().add("Hans Magne Asheim");
        refereeChoice2.getItems().add("Leon Hesthaug");
        refereeChoice2.setValue("Second Referee");
    }

    /**
     * method for when a new match is to be created
     */
    public void submitMatch(){
        String nameOfTeam1 = teamChoice1.getValue();
        Team team1 = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                .get(HandballApplication.chosenTournament).getTeamRegister().getTeams().get(nameOfTeam1);

        String nameOfTeam2 = teamChoice2.getValue();
        Team team2 = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                .get(HandballApplication.chosenTournament).getTeamRegister().getTeams().get(nameOfTeam2);

        int fieldNum = fieldChoice.getValue();

        try{
            Matcher m = hourMinPat.matcher(startTime.getCharacters());
            if(!m.matches()) throw new IllegalArgumentException("**Hour and minutes format is invalid**");
            if(dateField.getValue() == null) throw new IllegalArgumentException("Please enter a correct date!");
        }
        catch (IllegalArgumentException e){
            errorMessage.setText(e.getMessage());
            return;
        }
        errorMessage.setText("");

        LocalDateTime startDate = LocalDateTime.of(dateField.getValue().getYear(), dateField.getValue().getMonth(),
                dateField.getValue().getDayOfMonth(), createHourMinList(startTime.getCharacters()).get(0),
                createHourMinList(startTime.getCharacters()).get(1));

        referees.add(refereeChoice.getValue());
        referees.add(refereeChoice2.getValue());
        if (refereeChoice.equals(refereeChoice2)) AlertBox.alertError("You cant put the same referee twice");

        Match match = new Match(startDate, 1,team1, team2, matchList.size() + 1, fieldNum);

        HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                .get(HandballApplication.chosenTournament).addMatch(match);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    @FXML
    public void goToFrontPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("RegionController", event);
    }

    /**
     * method that sends program to specific screen
     * @param actionEvent button event
     * @throws IOException when path not found
     */
    @FXML
    public void goToTeamRegisterPage(ActionEvent actionEvent) throws IOException {
        SwitchScene.switchScene("TeamRegister", actionEvent);
    }

    /**
     * method that sends program to specific screen
     * @param actionEvent button event
     * @throws IOException when path not found
     */
    @FXML
    public void goToRegisterResult(ActionEvent actionEvent) throws IOException {
        SwitchScene.switchScene("RegisterResult", actionEvent);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void goMainPage(ActionEvent event) throws IOException{
        SwitchScene.switchScene("MainPage", event);
    }

    /**
     * method for log out
     */
    public void LogOutButton(){
        if(AlertBox.logOut() == 1){
            System.exit(-1);
        }
    }
    //TODO: CONNECT TO TEAM REGISTER DATABASE

    /**
     * This method takes in a string of format HH:MM and converts it to a list (index 2) of hours and minutes as ints.
     * @return                    List of hours and minutes, represented as Integers
     * @param stringToBeConverted Character sequence of HH:MM.
     */
    private List<Integer> createHourMinList(CharSequence stringToBeConverted){
        return Arrays.stream(stringToBeConverted.toString().split(":"))
                .map(Integer::valueOf).toList();
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void toHelpPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("HelpPage", event);
    }
}

//TODO: Link better. Currently the menu bar, makes the linking difficult. We could always replace with buttons
//TODO: link back to matches is broken.
//TODO: catching exceptions when submitting immediately is also broken