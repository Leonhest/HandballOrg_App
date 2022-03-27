package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RegisterResultController implements Initializable {
    private Tournament tournament;//todo must be choosen

    @FXML private TableView<Match> matchTable;
    @FXML private TableColumn<Match, LocalTime> matchTime;
    @FXML private TableColumn<Match, String> matchPlayers;
    @FXML private TableColumn<Match, Integer> matchID;
    @FXML private TableColumn<Match, String> scoreID;
    @FXML private ChoiceBox<String> winnerTeamChoiceBox;
    @FXML private TextField winnerGoalsInput;
    @FXML private ChoiceBox<String> loserTeamChoiceBox;
    @FXML private TextField loserGoalsInput;
    @FXML private TextField matchIDInput;
    @FXML private Button nextDateButton;
    @FXML private Button backToResultsButton;
    @FXML private Button submitButton;
    @FXML private Text feedBackText;

    /**
     * method for updating table view
     */
    @FXML
    private void updateTableView(){
        matchTime.setCellValueFactory(new PropertyValueFactory<Match, LocalTime>("time"));
        matchPlayers.setCellValueFactory(new PropertyValueFactory<Match, String>("players"));
        matchID.setCellValueFactory(new PropertyValueFactory<Match, Integer>("matchID"));
        scoreID.setCellValueFactory(new PropertyValueFactory<Match, String>("finalResult"));
        matchTable.setItems(FXCollections.observableArrayList(HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList()));
        matchTable.refresh();
        /*
       String matchScoreTxt = String.valueOf(new PropertyValueFactory<Match, String>("matchScore"));

       feedBackText.setText(matchScoreTxt);
       System.out.println(matchScoreTxt);
       if (matchScoreTxt == null || matchScoreTxt.isBlank()) {
       } else {
           matchScore.setCellValueFactory(new PropertyValueFactory<Match, String>("matchScore"));
       }
        */
    }

    /**
     * method for registering new results when a match is done
     */
    @FXML
    public void registerResult() {//todo: add check for the input - is the input integer?
        if(Integer.parseInt(winnerGoalsInput.getText()) >= Integer.parseInt(loserGoalsInput.getText())) {
            Match match = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList().stream().filter(m -> m.getMatchID() == Integer.parseInt(matchIDInput.getText())).collect(Collectors.toList()).get(0);
            match.setScore(winnerTeamChoiceBox.getValue(), Integer.parseInt(winnerGoalsInput.getText()));
            match.setScore(loserTeamChoiceBox.getValue(), Integer.parseInt(loserGoalsInput.getText()));
        } else {
            feedBackText.setFill(Color.RED);
            feedBackText.setText("*The winner result must be greater than the loser score*");
        }
        updateTableView();
    }

    /**
     * {@inheritDoc}
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Configuring the table
        updateTableView();

        winnerTeamChoiceBox.setValue("Winner");
        loserTeamChoiceBox.setValue("Loser");

        //todo: show just teams of interest when matchID is chosen
        List<Team> teams = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getTeamRegister().getListTeams();
        for (Team team:teams) {
            winnerTeamChoiceBox.getItems().add(team.getTeamName());
            loserTeamChoiceBox.getItems().add(team.getTeamName());
        }
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    @FXML
    public void toFrontPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("FrontPage", event);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void toMainPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("MainPage", event);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void toResults(ActionEvent event) throws IOException {
        SwitchScene.switchScene("RegisterResults", event);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void toCupList(ActionEvent event) throws IOException {
        SwitchScene.switchScene("CupList", event);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void toSetUpMatches(ActionEvent event) throws IOException {
        SwitchScene.switchScene("SetUpMatches", event);
    }

    /**
     * method for log out
     */
    public void logOutButton() {
        AlertBox.logOut();
        Platform.exit();
    }
}
