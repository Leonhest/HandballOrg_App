package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Match;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Team;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class RegisterResultController implements Initializable {
    private Tournament tournament;//todo must be choosen

    @FXML private TableView<Match> matchTable;
    @FXML private TableColumn<Match, LocalTime> matchTime;
    @FXML private TableColumn<Match, String> matchPlayers;
    @FXML private TableColumn<Match, Integer> matchID;
    @FXML private ChoiceBox<String> winnerTeamChoiceBox;
    @FXML private TextField winnerGoalsInput;
    @FXML private ChoiceBox<String> loserTeamChoiceBox;
    @FXML private TextField loserGoalsInput;
    @FXML private Button nextDateButton;
    @FXML private Button backToResultsButton;
    @FXML private Button submitButton;
    @FXML private Text feedBackText;


    ObservableList<Match> listTeams = FXCollections.observableArrayList(new Match(LocalDateTime.of(2022, 04, 22, 12, 0), 1,
            new Team("Asker FC", "Leon", "Asker", 7, 98059037),
            new Team("Sandefjord Gutta", "Trym", "Vestfold", 10, 98059038),
            1, 2), new Match(LocalDateTime.of(2022, 04, 22, 13, 0), 2,
            new Team("Singsaker FC", "Tomas", "Trøndelag", 8, 98059022),
            new Team("Sandnes", "Eirik", "Rogaland", 9, 9805901),
            2, 1));

    private void updateTableView(){
        matchTime.setCellValueFactory(new PropertyValueFactory<Match, LocalTime>("startTime"));
        matchPlayers.setCellValueFactory(new PropertyValueFactory<Match, String>("players"));
        matchID.setCellValueFactory(new PropertyValueFactory<Match, Integer>("matchID"));

       /*
       String matchScoreTxt = String.valueOf(new PropertyValueFactory<Match, String>("matchScore"));

       feedBackText.setText(matchScoreTxt);
       System.out.println(matchScoreTxt);
       if (matchScoreTxt == null || matchScoreTxt.isBlank()) {
       } else {
           matchScore.setCellValueFactory(new PropertyValueFactory<Match, String>("matchScore"));
       }
        */
        matchTable.setItems(listTeams);
    }

    public void registerResult() {
        if(Integer.parseInt(winnerGoalsInput.getText()) >= Integer.parseInt(loserGoalsInput.getText())) {
            feedBackText.setText("Result " + winnerTeamChoiceBox.getValue() + " vs. " + loserTeamChoiceBox.getValue() + " Score " + winnerGoalsInput.getText() + "-" + loserGoalsInput.getText());
        } else {
            feedBackText.setFill(Color.RED);
            feedBackText.setText("*The winner result must be greater than the loser score*");
        }
    }

    public void toFrontPage(ActionEvent event) throws IOException {
        //get the new scene
        Parent viewParent = FXMLLoader.load(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/MainPage.fxml"));
        Scene viewScene = new Scene(viewParent);

        //information of stage
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Configuring the table
        updateTableView();

        winnerTeamChoiceBox.setValue("Winner");
        loserTeamChoiceBox.setValue("Loser");

        //todo: remove this values when expanding the system
        winnerTeamChoiceBox.getItems().add("Oslo");
        winnerTeamChoiceBox.getItems().add("Sandefjord");
        loserTeamChoiceBox.getItems().add("Tjøme");
        loserTeamChoiceBox.getItems().add("Singsaker");
    }
}
