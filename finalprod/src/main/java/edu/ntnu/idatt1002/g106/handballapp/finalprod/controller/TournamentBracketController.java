package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TournamentBracketController implements Initializable {

    @FXML
    private TextArea match1round1;

    /**
     * {@inheritDoc}
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        match1round1.setText("Match 1 \n\n\n Match 2");

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
        System.out.println("Yeah boi");
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

}
