package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.Match;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * this class disused for generation of matches, and similar methods
 * @author Gruppe 6
 */
public class TournamentBracketController implements Initializable {


    @FXML
    private TextArea b4r1m1;

    @FXML
    private TextArea b4r1m2;

    @FXML
    private TextArea b4r2m1;

    private List<TextArea> bracket4Team;

    @FXML
    private TextArea b8r1m1;

    @FXML
    private TextArea b8r1m2;

    @FXML
    private TextArea b8r1m3;

    @FXML
    private TextArea b8r1m4;

    @FXML
    private TextArea b8r2m1;

    @FXML
    private TextArea b8r2m2;

    @FXML
    private TextArea b8r3m1;

    private List<TextArea> bracket8Team;

    @FXML
    private TextArea b16r1m1;

    @FXML
    private TextArea b16r1m2;

    @FXML
    private TextArea b16r1m3;

    @FXML
    private TextArea b16r1m4;

    @FXML
    private TextArea b16r1m5;

    @FXML
    private TextArea b16r1m6;

    @FXML
    private TextArea b16r1m7;

    @FXML
    private TextArea b16r1m8;

    @FXML
    private TextArea b16r2m1;

    @FXML
    private TextArea b16r2m2;

    @FXML
    private TextArea b16r2m3;

    @FXML
    private TextArea b16r2m4;

    @FXML
    private TextArea b16r3m1;

    @FXML
    private TextArea b16r3m2;

    @FXML
    private TextArea b16r4m1;

    private List<TextArea> bracket16Team;


    @FXML
    private TextArea b32r1m1;

    @FXML
    private TextArea b32r1m10;

    @FXML
    private TextArea b32r1m11;

    @FXML
    private TextArea b32r1m12;

    @FXML
    private TextArea b32r1m13;

    @FXML
    private TextArea b32r1m14;

    @FXML
    private TextArea b32r1m15;

    @FXML
    private TextArea b32r1m16;

    @FXML
    private TextArea b32r1m2;

    @FXML
    private TextArea b32r1m3;

    @FXML
    private TextArea b32r1m4;

    @FXML
    private TextArea b32r1m5;

    @FXML
    private TextArea b32r1m6;

    @FXML
    private TextArea b32r1m7;

    @FXML
    private TextArea b32r1m8;

    @FXML
    private TextArea b32r1m9;

    @FXML
    private TextArea b32r2m1;

    @FXML
    private TextArea b32r2m2;

    @FXML
    private TextArea b32r2m3;

    @FXML
    private TextArea b32r2m4;

    @FXML
    private TextArea b32r2m5;

    @FXML
    private TextArea b32r2m6;

    @FXML
    private TextArea b32r2m7;

    @FXML
    private TextArea b32r2m8;

    @FXML
    private TextArea b32r3m1;

    @FXML
    private TextArea b32r3m2;

    @FXML
    private TextArea b32r3m3;

    @FXML
    private TextArea b32r3m4;

    @FXML
    private TextArea b32r4m1;

    @FXML
    private TextArea b32r4m2;

    @FXML
    private TextArea b32r5m1;

    private List<TextArea> bracket32Team;

    @FXML
    private Button generateButton;

    @FXML
    private Text errorMessage;

    private static List<TextArea> currentBracket;


    /**
     * {@inheritDoc}
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeLists();

        int numTeams =  HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getNumTeams();
        if(numTeams == 4){
            currentBracket = bracket4Team;
        }
        else if(numTeams == 8){
            currentBracket = bracket8Team;
        }
        else if(numTeams == 16){
            currentBracket = bracket16Team;
        }
        else if(numTeams == 32){
            currentBracket = bracket32Team;
        }
        printMatches();
    }

    /**
     * this method initializes the chosen bracket list
     */
    private void initializeLists(){
        bracket4Team = new ArrayList<>(Arrays.asList(b4r1m1, b4r1m2, b4r2m1));
        bracket8Team = new ArrayList<>(Arrays.asList(b8r1m1, b8r1m2, b8r1m3, b8r1m4, b8r2m1, b8r2m2, b8r3m1));
        bracket16Team = new ArrayList<>(Arrays.asList(b16r1m1,b16r1m2, b16r1m3, b16r1m4, b16r1m5, b16r1m6, b16r1m7, b16r1m8,
                b16r2m1, b16r2m2, b16r2m3, b16r2m4, b16r3m1, b16r3m2, b16r4m1));
        bracket32Team = new ArrayList<>(Arrays.asList(b32r1m1, b32r1m2, b32r1m3, b32r1m4, b32r1m5,
                b32r1m6, b32r1m7, b32r1m8, b32r1m9, b32r1m10, b32r1m11, b32r1m12, b32r1m13, b32r1m14, b32r1m15, b32r1m16,
                b32r2m1, b32r2m2, b32r2m3, b32r2m4, b32r2m5, b32r2m6, b32r2m7, b32r2m8, b32r3m1, b32r3m2, b32r3m3, b32r3m4,
                b32r4m1, b32r4m2, b32r5m1));
    }

    /**
     * method for log out
     */
    public void logout(){
        if(AlertBox.logOut()==1){
            System.exit(-1);
        }
    }

    /**
     * this method is used for generating a next round of matches with remaining teams i  tournament
     */
    @FXML
    public void generateNextRound(){
        errorMessage.setText("");
        int roundNum = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getCurrentRound();
        try {
            HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).generateRoundWithTeams(roundNum);
        }catch (Exception e){
            errorMessage.setText(e.getMessage());
            errorMessage.setFill(Color.RED);
        }
        printMatches();
    }

    //TODO: generateButton.isVisible(false) when no more rounds can be generated?

    /**
     * method that prints matches onn the view board in the brackets
     */
    public void printMatches(){
        int roundNum = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getCurrentRound();
        int previousRoundNum = roundNum - 1;
        if(roundNum == 1 && HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                .get(HandballApplication.chosenTournament).getRoundTeamList().get(0).size() !=
                HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().
                        get(HandballApplication.chosenTournament).getNumTeams()){
            try {
                HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).generateRoundWithTeams(roundNum);
            }
            catch (IllegalArgumentException e){
                AlertBox.alertError(e.getMessage());
                //TODO: What's better this or a textLabel on the screen?
                disableGenerateButton();
                return;
            }
            previousRoundNum = 1;
        }


        int textIndex = 0;
        for(int i = 1; i <= previousRoundNum; i++){
            List<Match> listOfMatchesPerRound = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament).getRoundMatchList().get(i-1);

            for(Match match: listOfMatchesPerRound){
                currentBracket.get(textIndex).setText(match.getTeam1().getTeamName() + "\n" + match.getTeam2().getTeamName());
                textIndex++;
            }
        }
    }

    /**
     * If all the teams aren't registered yet, the generate button should be disabled.
     */
    public void disableGenerateButton(){
        generateButton.setDisable(true);
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
    public void sendToMainPage(ActionEvent event) throws  IOException {
        SwitchScene.switchScene("MainPage", event);
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    @FXML
    public void sendToRegisterTeam(ActionEvent event) throws IOException {
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
     * @param event button event
     * @throws IOException when path not found
     */
    public void sendToHelpPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("HelpPage", event);
    }
}
