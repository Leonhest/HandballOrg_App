package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
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
    }

    /**
     * method for registering new results when a match is done
     */
    @FXML
    public void registerResult(ActionEvent event) throws IOException {//todo: add check for the input - is the input integer?
        Match match = null;
        try {
            match = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList().stream().filter(m -> m.getMatchID() == Integer.parseInt(matchIDInput.getText())).collect(Collectors.toList()).get(0);

            if (Integer.parseInt(winnerGoalsInput.getText()) < 0 || Integer.parseInt(loserGoalsInput.getText()) < 0) {
                AlertBox.alertError("The goals can not be a negative value");
            }
           if (match == null) {
                AlertBox.alertError("Please check the input for match id");
            }
        } catch (NumberFormatException e) {
            AlertBox.alertError("Please check if the input field require an integer");
        } catch (IndexOutOfBoundsException e) {
            AlertBox.alertError("Please select an registered match");
        } catch (Exception e) {
            AlertBox.alertError("System fail");
        }

        match.setScore(winnerTeamChoiceBox.getValue(), Integer.parseInt(winnerGoalsInput.getText()));
        match.setScore(loserTeamChoiceBox.getValue(), Integer.parseInt(loserGoalsInput.getText()));
        updateTableView();
        Team winner = match.getWinner();
        HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getRoundTeamList().get(match.getRoundNum()-1).add(winner);
        SwitchScene.switchScene("MainPage", event);
    }

    //TODO: Fix when the submit button is pressed multiple times and make sure a match actually getds the right score

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

        //todo: show just teams of interest when matchID is chosen!!
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
        SwitchScene.switchScene(SwitchScene.getCurrentRegion(), event);
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
    public void toCupList(ActionEvent event) throws IOException {
        SwitchScene.switchScene("CupList", event);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void toSetUpMatches(ActionEvent event) throws IOException {
        int numTeams =  HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getNumTeams();
        if(numTeams == 4){
            SwitchScene.switchScene("TournamentBracket4", event);
        }
        else if(numTeams == 8){
            SwitchScene.switchScene("TournamentBracket8", event);
        }
        else if(numTeams == 16){
            SwitchScene.switchScene("TournamentBrackets16", event);
        }
        else if(numTeams == 32){
            SwitchScene.switchScene("TournamentBrackets32", event);
        };
    }

    /**
     * method for log out
     */
    public void logOutButton() {
        AlertBox.logOut();
        Platform.exit();
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void toHelpPage(ActionEvent event) throws IOException{
        SwitchScene.switchScene("HelpPage", event);
    }
}
