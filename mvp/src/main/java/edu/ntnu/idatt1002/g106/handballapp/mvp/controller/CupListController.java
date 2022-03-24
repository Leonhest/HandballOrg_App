package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.SwitchScene;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CupListController implements Initializable {

    //todo: change menu to button menu
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
    private TableColumn<Team, String> regionColumn;
    @FXML
    private TableColumn<Team, Integer> phoneNumColumn;

    private void updateTableView(){
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("teamName"));
        numPlayerColumn.setCellValueFactory(new PropertyValueFactory<Team, Integer>("numPlayers"));
        teamLeaderColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("teamLeader"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("region"));
        phoneNumColumn.setCellValueFactory(new PropertyValueFactory<Team, Integer>("telephoneNum"));
        //System.out.println("Size " + listTeams.size());
        teamTableView.setItems(FXCollections.observableArrayList(
                HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                        .get(HandballApplication.chosenTournament).getTeamRegister().getListTeams()));
        teamTableView.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Choice box for the numPlayers
        for(int i = 0; i < 9; i++){
            numPlayerInput.getItems().add(i,String.valueOf(i+7));
        }
        numPlayerInput.setValue("7");
        updateTableView();
    }

    @FXML
    public void goToFrontPage(ActionEvent actionEvent) throws IOException {
        SwitchScene.switchScene("MainPage", actionEvent);
    }

    @FXML
    public void goToMatchesPage(ActionEvent actionEvent) throws IOException {
        SwitchScene.switchScene("SetUpMatches", actionEvent);
    }

    @FXML
    public void goToResultsPage(ActionEvent actionEvent) throws IOException {
        SwitchScene.switchScene("RegisterResultPage", actionEvent);
    }

    @FXML
    public void goToTournamentPage(ActionEvent actionEvent) throws IOException {
        SwitchScene.switchScene("SetUpTournamentPage", actionEvent);
    }

    //TODO: Make one FXMLLoader class to take in the pathing!

    @FXML
    public void confirmAddNewTeam(){
        try{
            teamInfoExceptions();
            if(phoneNumTextFieldInput.getText().length() != 8) throw new IllegalArgumentException("*A phone number needs 8 digits!*");
            Team team = new Team(teamNameTextFieldInput.getText(), teamLeaderTextFieldInput.getText(),
                    regionTextFieldInput.getText(), Integer.valueOf(numPlayerInput.getValue()),
                    Integer.valueOf(phoneNumTextFieldInput.getText()));

            HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                    .get(HandballApplication.chosenTournament).getTeamRegister().addTeam(team);

            System.out.println("List inside method " + HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                    .get(HandballApplication.chosenTournament).getTeamRegister().getListTeams().size());
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

    @FXML
    public void resetInfo(){
        teamNameTextFieldInput.setText("");
        teamLeaderTextFieldInput.setText("");
        regionTextFieldInput.setText("");
        phoneNumTextFieldInput.setText("");
        numPlayerInput.setValue("7");
    }

    private void teamInfoExceptions() throws IllegalArgumentException{
        if(teamNameTextFieldInput.getText().isEmpty() || teamNameTextFieldInput.getText().isBlank()) throw new IllegalArgumentException("*Team Name is invalid!*");
        if(teamLeaderTextFieldInput.getText().isEmpty() || teamLeaderTextFieldInput.getText().isBlank()) throw new IllegalArgumentException("*Team Leader is invalid!*");
        if(regionTextFieldInput.getText().isEmpty() || regionTextFieldInput.getText().isBlank()) throw new IllegalArgumentException("*Region is invalid!*");
    }

}