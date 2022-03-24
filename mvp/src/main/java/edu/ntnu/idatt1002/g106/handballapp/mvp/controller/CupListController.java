package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

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
    @FXML
    private Button newTeamConfirm;

    ObservableList<Team> listTeams = FXCollections.observableArrayList(
            new Team("Asker FC", "Leon", "Asker", 7, 98059037),
            new Team("Sandefjord Gutta", "Trym", "Vestfold", 10, 98059038)
            );

    private void updateTableView(){
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("teamName"));
        numPlayerColumn.setCellValueFactory(new PropertyValueFactory<Team, Integer>("numPlayers"));
        teamLeaderColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("teamLeader"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("region"));
        phoneNumColumn.setCellValueFactory(new PropertyValueFactory<Team, Integer>("telephoneNum"));
        teamTableView.setItems(listTeams);
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
        switchScene("FrontPage", actionEvent);
    }

    @FXML
    public void goToMatchesPage(ActionEvent actionEvent) throws IOException {
        switchScene("SetUpPage", actionEvent);
    }

    @FXML
    public void goToResultsPage(ActionEvent actionEvent) throws IOException {
        switchScene("RegisterResultPage", actionEvent);
    }

    @FXML
    public void goToTournamentPage(ActionEvent actionEvent) throws IOException {
        switchScene("SetUpTournamentPage", actionEvent);
    }

    public void switchScene(String location, ActionEvent actionEvent) throws IOException {
        Parent viewPage = FXMLLoader.load(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/" + location + ".fxml"));
        Scene page = new Scene(viewPage);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(page);
        window.show();
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
    }

    @FXML
    public void resetInfo(){
        teamNameTextFieldInput.setText("");
        teamLeaderColumn.setText("");
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


//TODO: Don't allow duplicate teams (Maybe connect to teamRegister and use the guarantee there)!