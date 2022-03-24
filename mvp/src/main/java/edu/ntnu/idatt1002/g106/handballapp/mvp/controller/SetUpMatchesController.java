package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SetUpMatchesController implements Initializable {

    @FXML
    private ChoiceBox<Integer> fieldChoice;

    @FXML
    private ChoiceBox<String> refereeChoice;

    @FXML
    private ChoiceBox<String> teamChoice1;

    @FXML
    private ChoiceBox<String> teamChoice2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Choice box for the fields
        for(int i = 0; i < 5; i++){
            fieldChoice.getItems().add(i,i+1);
        }

        //Connecting a database to here wouldn't be too bad
        teamChoice1.getItems().add("Sandefjord");
        teamChoice1.getItems().add("Asker");
        teamChoice1.getItems().add("Tjøme");
        teamChoice1.getItems().add("Trondheim");
        teamChoice1.setValue("Team 1");

        teamChoice2.getItems().add("Sandefjord");
        teamChoice2.getItems().add("Asker");
        teamChoice2.getItems().add("Tjøme");
        teamChoice2.getItems().add("Trondheim");
        teamChoice2.setValue("Team 2");

        refereeChoice.getItems().add("Eirik Dommerstad");
        refereeChoice.getItems().add("Hans Magne Asheim");
        refereeChoice.getItems().add("Leon Hesthaug");
        refereeChoice.setValue("Referee");

    }


    @FXML
    public void goToFrontPage(ActionEvent actionEvent) throws IOException {
        switchScene("FrontPage", actionEvent);
    }

    @FXML
    public void goToCupListPage(ActionEvent actionEvent) throws IOException {
        switchScene("CupList", actionEvent);
    }

    @FXML
    public void goToMatchesPage(ActionEvent actionEvent) throws IOException {
        switchScene("SetUpPage", actionEvent);
    }

    @FXML
    public void goToRegisterResult(ActionEvent actionEvent) throws IOException {
        switchScene("RegisterResult", actionEvent);
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

}

//TODO: Link better. Currently the menu bar, makes the linking difficult. We could always replace with buttons