package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.Match;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * This is the mainpage class and hold information of matches, gets updated when initialized
 * @author Gruppe 6
 */
public class MainPageController implements Initializable {
    @FXML private TableView<Match> table;
    @FXML private TableColumn<Match, LocalDateTime> time;
    @FXML private TableColumn<Match, String> match;
    @FXML private TableColumn<Match, Integer> field;
    @FXML private TableColumn<Match, Integer> firstRef;
    @FXML private TableColumn<Match, Integer> secondRef;
    @FXML private DatePicker dateSelect;
    @FXML private Button refAdd;
    @FXML private TextField refField;
    @FXML private Button refAdd2;
    @FXML private TextField refField2;

    private Match matchSelected;

    /**
     * {@inheritDoc}
      * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTableView();
        trackTableClick();
    }

    /**
     * method that updates MainPages tableView
     */
    private void updateTableView() {
        time.setCellValueFactory(new PropertyValueFactory<Match, LocalDateTime>("time"));
        match.setCellValueFactory(new PropertyValueFactory<Match, String>("players"));
        field.setCellValueFactory(new PropertyValueFactory<Match, Integer>("numField"));
        firstRef.setCellValueFactory(new PropertyValueFactory<Match, Integer>("referee1"));
        secondRef.setCellValueFactory(new PropertyValueFactory<Match, Integer>("referee2"));
        table.setItems(FXCollections.observableArrayList(HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getMatchList()));
        table.refresh();
    }


    /**
     * This method attaches the event handler setOnMouseClicked on the match table in order to autofill the input
     * fields if a match is clicked.
     */
    public void trackTableClick(){
        table.setOnMouseClicked(event -> {
            matchSelected = table.getSelectionModel().getSelectedItem();
        });
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    @FXML
    public void sendToFrontPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("RegionController", event);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    @FXML
    public void sendToSetUpMatches(ActionEvent event) throws IOException {
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
        }
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    @FXML
    public void sendToRegisterTeam(ActionEvent event) throws IOException {
        System.out.println("klah");
        SwitchScene.switchScene("TeamRegister", event);
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
     * @throws IOException when path not found
     */
    @FXML
    public void selectDate() {
        if (dateSelect.getValue() == null) {
            AlertBox.alertError("No date register.\n" +
                    "Please select a date");
        } else if (dateSelect.getValue().isBefore(LocalDate.now())) {
            AlertBox.alertError("Not possible to select a day before current date");
        }
        //todo: implement functions
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void toHelpPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("HelpPage", event);
    }

    public void setFirstReferee(){
        if(matchSelected == null){
            throw new IllegalArgumentException("No match selected");
        }
        else{
            matchSelected.setReferee1(refField.getText());
            updateTableView();
        }
    }

    public void setSecondReferee(){
        if(matchSelected == null){
            throw new IllegalArgumentException("No match selected");
        }
        else{
            matchSelected.setReferee2(refField2.getText());
            updateTableView();
        }
    }

    /**
     * method for log out
     */
    public void logout(){
        if(AlertBox.logOut()==1){
            System.exit(-1);
        }
    }
}
