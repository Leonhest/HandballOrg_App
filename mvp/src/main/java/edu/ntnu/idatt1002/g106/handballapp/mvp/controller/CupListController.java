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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CupListController implements Initializable {

    //Text field for team name
    @FXML private TextField teamNameTextFieldInput;

    //Text field for team leader
    @FXML private TextField teamLeaderTextFieldInput;

    //Text field for region
    @FXML private TextField regionTextFieldInput;

    //Text field for phone number
    @FXML private TextField phoneNumTextFieldInput;

    //ChoiceBox for number of players
    @FXML private ChoiceBox<String> numPlayerInput;

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



    //Button for newTeamConfirm to confirm the adding of a new team
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
        Parent viewFrontPage = FXMLLoader.load(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/FrontPage.fxml"));
        Scene frontPage = new Scene(viewFrontPage);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(frontPage);
        window.show();
    }

    @FXML
    public void goToMatchesPage(ActionEvent actionEvent) throws IOException {
        Parent viewMatchesPage = FXMLLoader.load(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/SetUpPage.fxml"));
        Scene matchesPage = new Scene(viewMatchesPage);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(matchesPage);
        window.show();
    }

    @FXML
    public void goToResultsPage(ActionEvent actionEvent) throws IOException {
        Parent viewResultsPage = FXMLLoader.load(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/RegisterResultPage.fxml"));
        Scene resultsPage = new Scene(viewResultsPage);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(resultsPage);
        window.show();
    }

    @FXML
    public void goToTournamentPage(ActionEvent actionEvent) throws IOException {
        Parent viewTournamentPage = FXMLLoader.load(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/SetUpTournamentPage.fxml"));
        Scene tournamentPage = new Scene(viewTournamentPage);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(tournamentPage);
        window.show();
    }



    @FXML
    public void confirmAddNewTeam(){
        Team team = new Team(teamNameTextFieldInput.getText(), teamLeaderTextFieldInput.getText(),
                regionTextFieldInput.getText(), Integer.valueOf(numPlayerInput.getValue()),
                Integer.valueOf(phoneNumTextFieldInput.getText()));

        listTeams.add(team);
    }
}


//TODO: Connect reset button to removing data stuff
//TODO: Make sure the data for team is valid.
//TODO: Don't allow duplicate teams