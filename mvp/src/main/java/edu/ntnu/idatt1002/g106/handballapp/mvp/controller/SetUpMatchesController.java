package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.SwitchScene;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Match;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Team;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class SetUpMatchesController implements Initializable {

    List<Match> matchList = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList();


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
    private ChoiceBox<String> teamChoice1;

    @FXML
    private ChoiceBox<String> teamChoice2;

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

        //Connecting a database to here wouldn't be too bad
        teamChoice1.getItems().add("Sandefjord");
        teamChoice1.getItems().add("Asker");
        teamChoice1.getItems().add("Tjøme");
        teamChoice1.getItems().add("Trondheim");
        teamChoice1.setValue("Team 1");

        teamChoice2.getItems().add("Sandefjord");
        teamChoice2.getItems().add("Asker");
        teamChoice2.getItems().add("Tjøme");
        teamChoice2.getItems().add("Trondheim");
        teamChoice2.setValue("Team 2");

        refereeChoice.getItems().add("Eirik Dommerstad");
        refereeChoice.getItems().add("Hans Magne Asheim");
        refereeChoice.getItems().add("Leon Hesthaug");
        refereeChoice.setValue("Referee");

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
        LocalDateTime startDate = null;
        String refereeName = refereeChoice.getValue();

        Match match = new Match(startDate, 1,team1, team2, 0, fieldNum);

        HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                .get(HandballApplication.chosenTournament).addMatch(match);
    }

    /**
     * method that sends program to specific screen
     * @param actionEvent button event
     * @throws IOException when path not found
     */
    @FXML
    public void goToFrontPage(ActionEvent actionEvent) throws IOException {
        SwitchScene.switchScene("FrontPage", actionEvent);
    }

    /**
     * method that sends program to specific screen
     * @param actionEvent button event
     * @throws IOException when path not found
     */
    @FXML
    public void goToCupListPage(ActionEvent actionEvent) throws IOException {
        SwitchScene.switchScene("CupList", actionEvent);
    }

    /**
     * method that sends program to specific screen
     * @param actionEvent button event
     * @throws IOException when path not found
     */
    @FXML
    public void goToSetUpMatchesPage(ActionEvent actionEvent) throws IOException {
        SwitchScene.switchScene("SetUpPageMatches", actionEvent);
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
        AlertBox.logOut();
        Platform.exit();
    }
    //TODO: CONNECT TO TEAM REGISTER DATABASE

}

//TODO: Link better. Currently the menu bar, makes the linking difficult. We could always replace with buttons