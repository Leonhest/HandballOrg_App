package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Match;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Team;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class RegisterResultController implements Initializable {
    private Tournament tournament;//todo must be choosen

    @FXML private TableView<Match> matchTable;
    @FXML private TableColumn<Match, LocalTime> matchTime;
    @FXML private TableColumn<Match, String> matchPlayers;
    @FXML private TableColumn<Match, String> matchScore;
    @FXML private ChoiceBox<String> winnerTeamChoiceBox;
    @FXML private TextField winnerGoalsInput;
    @FXML private ChoiceBox<String> loserTeamChoiceBox;
    @FXML private TextField loserGoalsInput;
    @FXML private Button nextDateButton;
    @FXML private Button backToResultsButton;
    @FXML private Button submitButton;
    @FXML private Text feedBackText;

    /*
    private void fillInnDataToTable() {
        for (Match matchResult:tournament.getResults().getMatchResults().values()) {
            matchTime.getColumns().add(matchResult.getStartTime());
            matchPlayers.getColumns().add(matchResult.getPlayers());
            String result = matchResult.getFinalResult();
            if (result == null) {
                matchScore.getColumns().add("No results");//todo: add a button for register results
            } else {
                matchScore.getColumns().add(result);
            }
        }
    }
     */

    private void addDataMVP() {
        this.tournament = new Tournament(1, LocalDate.now(), LocalDate.of(2022, 04, 22), "Layout1", "Asker", 3, 10);
        Team team1 = new Team("Oslo", "Eirik", "Oslo", 13, 95876522);
        Team team2 = new Team("Sandefjord", "Tomas", "Oslo", 11, 95876521);
        tournament.addMatch(new Match(LocalTime.of(12, 0), 1, team1, team2, 1, 1));
    }

    private void fillInnTeams(ChoiceBox box) {
        for (Match matchResult:tournament.getResults().getMatchResults().values()) {
            box.getItems().add(matchResult.getTeam1());
            box.getItems().add(matchResult.getTeam2());
        }
    }

    public void registerResult() {
        if(Integer.parseInt(winnerGoalsInput.getText()) >= Integer.parseInt(loserGoalsInput.getText())) {
            feedBackText.setText("Result " + winnerTeamChoiceBox.getValue() + " vs. " + loserTeamChoiceBox.getValue() + " Score " + winnerGoalsInput.getText() + "-" + loserGoalsInput.getText());
        } else {
            feedBackText.setFill(Color.RED);
            feedBackText.setText("*The winner result must be greater than the loser score*");
        }
    }

    public void toFrontPage(ActionEvent event) throws IOException {
        //get the new scene
        Parent viewParent = FXMLLoader.load(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/MainPage.fxml"));
        Scene viewScene = new Scene(viewParent);

        //information of stage
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* //todo: can be added when code for choosing tournament is choose

        //Configuring the choiceBox
        fillInnTeams(winnerTeamChoiceBox);
        fillInnTeams(loserTeamChoiceBox);
        fillInnGoalsOptions(winnerGoalsChoiceBox);
        fillInnGoalsOptions(loserGoalsChoiceBox);
        //Sets default values



         */

        //Configuring the table
        addDataMVP();//todo: remove later
        //fillInnDataToTable();

        winnerTeamChoiceBox.setValue("Winner");
        loserTeamChoiceBox.setValue("Loser");

        //todo: remove this values when expanding the system
        winnerTeamChoiceBox.getItems().add("Oslo");
        winnerTeamChoiceBox.getItems().add("Sandefjord");
        loserTeamChoiceBox.getItems().add("Tjøme");
        loserTeamChoiceBox.getItems().add("Singsaker");
    }
}
