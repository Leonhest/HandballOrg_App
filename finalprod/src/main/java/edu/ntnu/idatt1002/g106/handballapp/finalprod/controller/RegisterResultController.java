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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
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

    private List<ChoiceBox<String>> choiceBoxes = new ArrayList<>();
    private int chosenChoiceBox;

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
        boolean informationValid = true;
        try {
            match = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList().stream().filter(m -> m.getMatchID() == Integer.parseInt(matchIDInput.getText())).collect(Collectors.toList()).get(0);

            if (Integer.parseInt(leftGoalsInput.getText()) < 0 || Integer.parseInt(rightGoalsInput.getText()) < 0) {
                AlertBox.alertError("The goals can not be a negative value");
            }
            if (match == null) {
                AlertBox.alertError("Please check the input for match id");
            }

        } catch (NumberFormatException e) {
            informationValid = false;
            AlertBox.alertError("Please check if the input field require an integer");
        } catch (IndexOutOfBoundsException e) {
            informationValid = false;
            AlertBox.alertError("Please select an registered match");
        } catch (Exception e) {
            informationValid = false;
            AlertBox.alertError("System fail");
        }

        if(informationValid) {
            match.setScore(leftTeamChoiceBox.getValue(), Integer.parseInt(leftGoalsInput.getText()));
            match.setScore(rightTeamChoiceBox.getValue(), Integer.parseInt(rightGoalsInput.getText()));

            HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).updateAllTeamsInTournament();

            updateTableView();
            Team winningTeam = match.getWinner();

            HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getRoundTeamList().get(match.getRoundNum()).add(winningTeam);
        }
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

        //Configuring the table
        chosenChoiceBox = -1;
        updateTableView();

        choiceBoxes.add(leftTeamChoiceBox);
        choiceBoxes.add(rightTeamChoiceBox);

        trackTableClick();
        trackMatchIDInput();
        trackTeamInput(leftTeamChoiceBox);
        trackTeamInput(rightTeamChoiceBox);

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

    /**
     * This method places a listener on the matchID input text field. Using the input, if a match with the given ID
     * exists, then the matches are pre-set.
     */
    public void trackMatchIDInput(){
        leftTeamChoiceBox.getItems().clear();
        rightTeamChoiceBox.getItems().clear();
        AtomicBoolean matchIDExists = new AtomicBoolean(false);
        matchIDInput.textProperty().addListener(((observableValue, previousInput, newInput) ->{
            for(Match match : HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList()){
                if(newInput.equals("")){
                    setInfo();
                    return;
                }
                else if(Integer.parseInt(newInput) == match.getMatchID()){
                    leftTeamChoiceBox.getItems().clear();
                    rightTeamChoiceBox.getItems().clear();
                    leftTeamChoiceBox.setValue(match.getTeam1().getTeamName());
                    rightTeamChoiceBox.setValue(match.getTeam2().getTeamName());
                    matchIDExists.set(true);
                }
            }
        } ));
        if(!matchIDExists.get()){
            setInfo();
        }
    }

    /**
     * This method places a listener on the two team input choice boxes. Using the input, the other team choices are
     * reduced and when two teams are chosen, then the matchID is automatically set.
     */
    public void trackTeamInput(ChoiceBox<String> teamChoice){
        ChoiceBox<String> otherChoiceBox;
        if(chosenChoiceBox == -1){
            if(teamChoice.getValue() == "Team 1") chosenChoiceBox = 0;
            else chosenChoiceBox = 1;
        }

        if(chosenChoiceBox == 0){
            otherChoiceBox = choiceBoxes.get(1);
        }
        else{
            otherChoiceBox = choiceBoxes.get(0);
        }


        //The chosenChoiceBox keeps all teams.

        AtomicInteger potentialUniqueMatchID = new AtomicInteger(-1);

        teamChoice.valueProperty().addListener(((observableValue, previousInput, newInput) ->{
            if(teamChoice != otherChoiceBox) {
                otherChoiceBox.getItems().clear();
                for (Match match : HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList()) {
                    if (match.hasTeam(newInput)) {
                        if (match.getTeam1().getTeamName().equals(newInput))
                            otherChoiceBox.getItems().add(match.getTeam2().getTeamName());
                        else otherChoiceBox.getItems().add(match.getTeam1().getTeamName());
                        potentialUniqueMatchID.set(match.getMatchID());
                    }
                }
            }
            else{
                for (Match match : HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList()) {
                    if (match.hasTeam(newInput) && match.hasTeam(choiceBoxes.get(chosenChoiceBox).getValue())) {
                        matchIDInput.setText(String.valueOf(match.getMatchID()));
                    }
                }
            }
        } ));

//        if(otherChoiceBox.getItems().size() == 1){
//            matchIDInput.setText(potentialUniqueMatchID.toString());
//        }
        //This function could be implemented where if there is only one option it is automatically chosen

    }

    /**
     * This method attaches the event handler setOnMouseClicked on the match table in order to autofill the input
     * fields if a match is clicked.
     */
    public void trackTableClick(){
        matchTable.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 ){
                matchSelected = matchTable.getSelectionModel().getSelectedItem();
                insertSelectedMatchInfo(matchSelected);
            }
        });
    }

    /**
     * This method sets the standard input for the team text fields.
     */
    public void setInfo(){
        leftTeamChoiceBox.setValue("Team 1");
        rightTeamChoiceBox.setValue("Team 2");

        List<Team> teams = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getTeamRegister().getListTeams();
        for (Team team:teams) {
            leftTeamChoiceBox.getItems().add(team.getTeamName());
            rightTeamChoiceBox.getItems().add(team.getTeamName());
        }
    }
}



/*
 If a team is selected, then all the teams which play against that team is placed in the other choice box.
 If two teams are picked, then the match ID is automatically set.
 */

//    Team teamSelected = HandballApplication.adminList.get(0).getTournamentRegister()
//            .getTournaments().get(HandballApplication.chosenTournament).getTeamRegister()
//            .findTeamBasedOnTeamName(teamChoice.getValue());


/*
    Improvement: Ability to change if mistakes were made such as after generating a new round, the administrator realizes
    they put the wrong score for the wrong team or something
*/
