package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.Tournament;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * this class has the different methods and fields necessary for creating new tournaments
 * @author Gruppe 6
 */
public class SetUpTournamentController implements Initializable {

    private Tournament tournament = null;

    //trengs denne?
    ArrayList<Tournament> observerList = new ArrayList<>();

    @FXML
    private TextField tournamentNameTextFieldInput;
    @FXML
    private TextField tournamentPlaceTextFieldInput;
    @FXML
    private ChoiceBox<String> tournamentLayoutInput;
    @FXML
    private ChoiceBox<Integer> tournamentNumFieldsInput;
    @FXML
    private DatePicker tournamentStartDateInput;
    @FXML
    private DatePicker tournamentEndDateInput;



    @FXML
    private ImageView layoutImageView;
    @FXML
    private String choosenFormat;
    @FXML
    private Text layoutText;

    /**
     * {@inheritDoc}
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        tournamentLayoutInput.getItems().add(0, "Layout 1: Max 4 teams");
        tournamentLayoutInput.getItems().add(1, "Layout 2: Max 8 teams");
        tournamentLayoutInput.getItems().add(2, "Layout 3: Max 16 teams");
        tournamentLayoutInput.getItems().add(3, "Layout 4: Max 32 teams");


        tournamentLayoutInput.setValue("Layout 1: Max 4 teams");

        //observerList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            tournamentNumFieldsInput.getItems().add(i, i + 1);
        }

        tournamentNumFieldsInput.setValue(1);
    }

    /**
     * Method sets an image by the image name
     * The method requires that all images is located in the resources under {@code TournamentPictures} and that the image is png
     * @param imageName The name of the image
     * @return The image if found
     */
    private Image getImageByName(String imageName) {
        return new Image("file:src/main/resources/edu/ntnu/idatt1002/g106/handballapp/finalprod/TournamentPictures/" + imageName +  ".png");
    }

    @FXML
    public void setImageOn() {
        tournamentLayoutInput.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2){
                choosenFormat = tournamentLayoutInput.getItems().get((Integer) number2);
                if(choosenFormat.equals("Layout 1: Max 4 teams")){
                    layoutImageView.setImage(getImageByName("Layout1"));
                    layoutText.setText(choosenFormat.substring(9));
                }
                if(choosenFormat.equals("Layout 2: Max 8 teams")){
                    layoutImageView.setImage(getImageByName("Layout2"));
                    layoutText.setText(choosenFormat.substring(9));
                }
                if(choosenFormat.equals("Layout 3: Max 16 teams")){
                    layoutImageView.setImage(getImageByName("Layout3"));
                    layoutText.setText(choosenFormat.substring(9));
                }
                if(choosenFormat.equals("Layout 4: Max 32 teams")){
                    layoutImageView.setImage(getImageByName("Layout4"));
                    layoutText.setText(choosenFormat.substring(9));
                }
            }
        });

    }

    /**
     * method for confirming when a new tournament i to be created
     * @param actionEvent button event
     * @throws IOException
     */
    @FXML
    public void confirmTournament (ActionEvent actionEvent) throws IOException {
        //TODO Add tournamentID check
        boolean correctInformation = false;
        String tournamentLayout = tournamentLayoutInput.getValue();
        String tournamentName = tournamentNameTextFieldInput.getText();
        String tournamentPlace = tournamentPlaceTextFieldInput.getText();
        int tournamentNumFields = tournamentNumFieldsInput.getValue();
        LocalDate tournamentStartDate = tournamentStartDateInput.getValue();
        LocalDate tournamentEndDate = tournamentEndDateInput.getValue();

        int tournamentNumTeams;

        if(Objects.equals(tournamentLayoutInput.getValue() , tournamentLayoutInput.getItems().get(0))){
            tournamentNumTeams = 4;
        }
        else if(Objects.equals(tournamentLayoutInput.getValue() , tournamentLayoutInput.getItems().get(1))){
            tournamentNumTeams = 8;
        }
        else if(Objects.equals(tournamentLayoutInput.getValue() , tournamentLayoutInput.getItems().get(2))){
            tournamentNumTeams = 16;
        }
        else {
            tournamentNumTeams = 32;

        }

        try {
            tournament = new Tournament(HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().size(), tournamentName, tournamentStartDate,
                    tournamentEndDate, tournamentLayout, tournamentPlace, tournamentNumFields, tournamentNumTeams, HandballApplication.chosenRegion.getRegionTxt());
            correctInformation = true;
        } catch (NullPointerException e) {
            AlertBox.alertError("Remember to fill in all information");
        } catch (IllegalArgumentException e) {
            AlertBox.alertError(e.getMessage());
        } catch (Exception e){
            AlertBox.alertError("System fail");
            return;
        }
        if (correctInformation) {
            HandballApplication.adminList.get(0).getTournamentRegister().addTournament(tournament);
            HandballApplication.setChosenTournament(tournament.getTournamentID());
            tournament.generateTournament();
            SwitchScene.switchScene("MainPage", actionEvent);
        }
    }

    /**
     * method that sends program to specific screen
     * @param event button event
     * @throws IOException when path not found
     */
    public void toRegionChoice(ActionEvent event) throws IOException {
        SwitchScene.switchScene(HandballApplication.chosenRegion.getRegionTxt(), event);
    }

    /**
     * log out method
     */
    @FXML
    private void logOutButton(){
        if (AlertBox.logOut() == 1){
            System.exit(-1);
        }
    }

}

//TODO: Exception handling for invalid tournament set up