package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.Team;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * this class handles team registration and different methods for user
 * team registration will happen in the fxml page and is used here
 * @author Gruppe 6
 */
public class TeamRegisterController implements Initializable {

    @FXML
    private Text feedbackText;
    @FXML
    private TextField teamNameTextFieldInput;
    @FXML
    private TextField teamLeaderTextFieldInput;
    @FXML
    private TextField regionTextFieldInput;
    @FXML
    private TextField phoneNumTextFieldInput;
    @FXML
    private ChoiceBox<String> numPlayerInput;
    //TODO: could change this choicebox to int and make the code a bit better, but functional right now

    @FXML
    private TableView<Team> teamTableView;
    @FXML
    private TableColumn<Team, String> teamNameColumn;
    @FXML
    private TableColumn<Team, Integer> numPlayerColumn;
    @FXML
    private TableColumn<Team, String> teamLeaderColumn;
    @FXML
    private TableColumn<Team, String> placeColumn;
    @FXML
    private TableColumn<Team, Integer> phoneNumColumn;

    /**
     * {@inheritDocq}
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Choice box for the numPlayers
        for(int i = 0; i < 8; i++){
            numPlayerInput.getItems().add(i,String.valueOf(i+7));
        }
        numPlayerInput.setValue("7");
        updateTableView();
    }

    /**
     * method for updating the table view for new teams registries
     */
    private void updateTableView(){
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("teamName"));
        numPlayerColumn.setCellValueFactory(new PropertyValueFactory<Team, Integer>("numPlayers"));
        teamLeaderColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("teamLeader"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("region"));
        phoneNumColumn.setCellValueFactory(new PropertyValueFactory<Team, Integer>("telephoneNum"));

        teamTableView.setItems(FXCollections.observableArrayList(
                HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                        .get(HandballApplication.chosenTournament).getTeamRegister().getListTeams()));
        teamTableView.refresh();
    }

    //TODO: Make one FXMLLoader class to take in the pathing!

    /**
     * method that confirms when new teams is to bee registered
     */
    @FXML
    public void confirmAddNewTeam(){
        try{
            teamInfoExceptions();
            Team team = new Team(teamNameTextFieldInput.getText(), teamLeaderTextFieldInput.getText(),
                    regionTextFieldInput.getText(), Integer.valueOf(numPlayerInput.getValue()),
                    Integer.valueOf(phoneNumTextFieldInput.getText()));
            if(phoneNumTextFieldInput.getText().length() != 8) throw new IllegalArgumentException("*A phone number needs 8 digits!*");
            else if(HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                    .get(HandballApplication.chosenTournament).getTeamRegister().getListTeams().size() == HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                    .get(HandballApplication.chosenTournament).getNumTeams()) {
                feedbackText.setFill(Color.RED);
                feedbackText.setText("No more teams can be added");
            }
            else{
                HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                        .get(HandballApplication.chosenTournament).getTeamRegister().addTeam(team);
                feedbackText.setText("");
                resetInfo();
                AlertBox.confirmBox("Your team has been registered");
            }

        }
        catch (IllegalArgumentException e){
            if(e.getMessage().contains("For input string")){
                feedbackText.setFill(Color.RED);
                feedbackText.setText("*Phone number is invalid*");
            }
            else{
                feedbackText.setFill(Color.RED);
                feedbackText.setText(e.getMessage());
            }
        }
        updateTableView();
    }

    /**
     * method that resets information written in text boxes
     */
    @FXML
    public void resetInfo(){
        teamNameTextFieldInput.clear();
        teamLeaderTextFieldInput.clear();
        regionTextFieldInput.clear();
        phoneNumTextFieldInput.clear();
        numPlayerInput.setValue(String.valueOf(7));
    }

    /**
     * methods that throws exceptions when needed
     * @throws IllegalArgumentException in different scenarios
     */
    private void teamInfoExceptions() throws IllegalArgumentException{
        if(teamNameTextFieldInput.getText().isEmpty() || teamNameTextFieldInput.getText().isBlank()) throw new IllegalArgumentException("*Team Name is invalid!*");
        if(teamLeaderTextFieldInput.getText().isEmpty() || teamLeaderTextFieldInput.getText().isBlank()) throw new IllegalArgumentException("*Team Leader is invalid!*");
        if(regionTextFieldInput.getText().isEmpty() || regionTextFieldInput.getText().isBlank()) throw new IllegalArgumentException("*Region is invalid!*");
        HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().forEach(tour -> tour.getTeamRegister().getListTeams()
                .forEach(team -> {
                    if(Objects.equals(team.getTeamName(), teamNameTextFieldInput.getText())) throw new IllegalArgumentException("Team is already registered!");
                    else if (team.getTelephoneNum() == Integer.parseInt(phoneNumTextFieldInput.getText())) throw new IllegalArgumentException("*Phone number taken*");
                }));
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void toFrontPage(ActionEvent event) throws IOException{
        SwitchScene.switchScene("RegionController", event);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void toMainPage(ActionEvent event) throws IOException{
        SwitchScene.switchScene("MainPage", event);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void toResults(ActionEvent event) throws IOException{
        SwitchScene.switchScene("RegisterResult", event);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void toMatches(ActionEvent event) throws IOException{
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
     * method that send the user so the help page
     * @param event any event
     * @throws IOException when path not found
     */
    public void toHelpPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("HelpPage", event);
    }
}