package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RegisterResultController implements Initializable {
    private Tournament tournament;

    @FXML private TableView<Match> matchTable;
    @FXML private TableColumn<Match, LocalTime> matchTime;
    @FXML private TableColumn<Match, String> matchPlayers;
    @FXML private TableColumn<Match, Integer> matchID;
    @FXML private TableColumn<Match, String> scoreID;
    @FXML private ChoiceBox<String> leftTeamChoiceBox;
    @FXML private TextField leftGoalsInput;
    @FXML private ChoiceBox<String> rightTeamChoiceBox;
    @FXML private TextField rightGoalsInput;
    @FXML private TextField matchIDInput;
    @FXML private Button nextDateButton;
    @FXML private Button backToResultsButton;
    @FXML private Button submitButton;
    @FXML private Text feedBackText;

    private Match matchSelected;

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
    public void registerResult() {
        Match match = null;
        try {
            match = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList().stream().filter(m -> m.getMatchID() == Integer.parseInt(matchIDInput.getText())).collect(Collectors.toList()).get(0);

            if (Integer.parseInt(leftGoalsInput.getText()) < 0 || Integer.parseInt(rightGoalsInput.getText()) < 0) {
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

        match.setScore(leftTeamChoiceBox.getValue(), Integer.parseInt(leftGoalsInput.getText()));
        match.setScore(rightTeamChoiceBox.getValue(), Integer.parseInt(rightGoalsInput.getText()));

        HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).updateAllTeamsInTournament();

        updateTableView();
        Team winningTeam = match.getWinner();

        HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getRoundTeamList().get(match.getRoundNum()).add(winningTeam);

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

        leftTeamChoiceBox.setValue("Team 1");
        rightTeamChoiceBox.setValue("Team 2");

            //todo: show just teams of interest when matchID is chosen
        List<Team> teams = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getTeamRegister().getListTeams();
        for (Team team:teams) {
            leftTeamChoiceBox.getItems().add(team.getTeamName());
            rightTeamChoiceBox.getItems().add(team.getTeamName());
        }

        matchTable.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 ){
                matchSelected = matchTable.getSelectionModel().getSelectedItem();
                insertSelectedMatchInfo(matchSelected);
            }
        });

    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    @FXML
    public void toFrontPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("RegionController", event);
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
    public void toTeamRegister(ActionEvent event) throws IOException {
        SwitchScene.switchScene("TeamRegister", event);
    }

    public void setSelectedMatch() {
        if (matchTable.getSelectionModel().getSelectedItem() == null){
            AlertBox.alertError("No match selected");
            return;
        }
        insertSelectedMatchInfo(matchTable.getSelectionModel().getSelectedItem());
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
     * This method changes the selections for the register result input boxes
     * @param match Selected match
     */
    public void insertSelectedMatchInfo(Match match){
        leftTeamChoiceBox.setValue(match.getTeam1().getTeamName());
        rightTeamChoiceBox.setValue(match.getTeam2().getTeamName());
        matchIDInput.setText(String.valueOf(match.getMatchID()));
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
