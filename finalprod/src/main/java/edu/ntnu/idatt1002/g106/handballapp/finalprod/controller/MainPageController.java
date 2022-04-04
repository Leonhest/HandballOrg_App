package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.Match;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    @FXML private TableView<Match> table;
    @FXML private TableColumn<Match, LocalDateTime> time;
    @FXML private TableColumn<Match, String> match;
    @FXML private TableColumn<Match, Integer> field;

    //ObservableList<Match> listTeams = FXCollections.observableArrayList(HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList());

    /**
     * method that updates MainPages tableView
     */
    private void updateTableView() {
        time.setCellValueFactory(new PropertyValueFactory<Match, LocalDateTime>("time"));
        match.setCellValueFactory(new PropertyValueFactory<Match, String>("players"));
        field.setCellValueFactory(new PropertyValueFactory<Match, Integer>("numField"));
        table.setItems(FXCollections.observableArrayList(HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList()));
        table.refresh();
    }

    //TODO: take code from Trym and use them to make list of the different teams, and make phone-number as REGEX

    /**
     * {@inheritDoc}
      * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTableView();
    }

    //TODO: make button log close program

    /**
     * method for log out
     */
    public void logout(){
        if(AlertBox.logOut()==1){
            System.exit(-1);
        }
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    @FXML
    public void sendToFrontPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene(SwitchScene.getCurrentRegion(), event);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    @FXML
    public void sendToSetUpMatches(ActionEvent event) throws IOException {
        SwitchScene.switchScene("SetUpMatches", event);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    @FXML
    public void sendToRegisterTeam(ActionEvent event) throws IOException {
        SwitchScene.switchScene("CupList", event);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    @FXML
    public void sendToRegisterResult(ActionEvent event) throws IOException {
        SwitchScene.switchScene("RegisterResult", event);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    @FXML
    public void SelectDate(ActionEvent event) {

    }
}
