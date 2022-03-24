package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Match;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.SwitchScene;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    @FXML private TableView<Match> table;
    @FXML private TableColumn<Match, LocalDateTime> time;
    @FXML private TableColumn<Match, String> match;
    @FXML private TableColumn<Match, Integer> field;

    //ObservableList<Match> listTeams = FXCollections.observableArrayList(HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList());

    private void updateTableView() {
        time.setCellValueFactory(new PropertyValueFactory<Match, LocalDateTime>("time"));
        match.setCellValueFactory(new PropertyValueFactory<Match, String>("players"));
        field.setCellValueFactory(new PropertyValueFactory<Match, Integer>("numField"));
        table.setItems(FXCollections.observableArrayList(HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList()));
        table.refresh();
    }

    //TODO: take code from Trym and use them to make list of the different teams, and make phone-number as REGEX
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTableView();
    }

    //TODO: make button log close program
    public void logout(){
        AlertBox.logOut();
    }

    @FXML
    public void sendToFrontPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("FrontPage", event);
    }

    @FXML
    public void sendToSetUpMatches(ActionEvent event) throws IOException {
        SwitchScene.switchScene("SetUpMatches", event);
    }

    @FXML
    public void sendToRegisterTeam(ActionEvent event) throws IOException {
        SwitchScene.switchScene("CupList", event);
    }

    @FXML
    public void sendToRegisterResult(ActionEvent event) throws IOException {
        SwitchScene.switchScene("RegisterResult", event);
    }

    @FXML
    public void SelectDate(ActionEvent event) {

    }
}
