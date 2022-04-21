package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.*;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.fileHandling.HandBallAppFileHandling;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Starting class extends application. hase basic information that fills the application
 * @author Gruppe 6
 */
public class HandballApplication extends Application {

    public static List<Administrator> adminList = new ArrayList<>();
    public static int chosenTournament = -1;
    public static Region chosenRegion = null;

    /**
     * main method that starts the application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * stating method with starting screen
     * @param stage starting stage
     * @throws Exception when path not found
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/finalprod/view/RegionChoice.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Tournament");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * {@inheritDoc}
     * @throws Exception
     */
    @Override
    public void init() throws Exception{
        super.init();
        startData();
    }

    /**
     * {@inheritDoc}
     * @throws Exception
     */
    @Override
    public void stop() throws Exception{
        super.stop();
    }

    /**
     * Method for removing expired tournaments from the tournamentRegister given in {@code param}
     * @param tournamentRegister TournamentRegister to check for expired tournaments and eventually remove tournaments from
     */
    private static TournamentRegister removeExpiredTournaments(TournamentRegister tournamentRegister) {
        tournamentRegister.getTournaments().removeIf(tournament -> LocalDate.now().isAfter(tournament.getEndDate()) && tournamentRegister.getTournaments().contains(tournament));
        return tournamentRegister;
    }

    /**
     * Method for starting data saved in program file
     */
    private static void startData(){
        Administrator admin = new Administrator(new User("Leon", "Hest", "123", "Leon.hesthaug@gmail.com"));
        adminList.add(admin);

        TournamentRegister tournamentRegister = null;
        try {
            tournamentRegister = HandBallAppFileHandling.deserializeTournamentRegister(new File("src/main/resources/edu/ntnu/idatt1002/g106/handballapp/finalprod/data/HandBallData.ser"));
            if (tournamentRegister != null) {
                HandballApplication.adminList.get(0).getTournamentRegister().addListOfTournaments(removeExpiredTournaments(tournamentRegister).getTournaments());
            }
        } catch (Exception e) {
            AlertBox.alertError("Could not load data");
        }
    }

    /**
     * method that sets tournament to a specific tournament ID
     * @param tournamentID id of the wanted tournament
     */
    public static void setChosenTournament(int tournamentID){
        chosenTournament = tournamentID;
    }

    /**
     * Setter for the chosen region
     * @param region chosen region
     */
    public static void setChosenRegion(String region) {
        if (Region.findRegion(region) == null) {
            throw new IllegalArgumentException("The chosen region does not match a valid region");
        }
        chosenRegion = Region.findRegion(region);
    }

    /**
     * method that removes spaces when needed, used for creating an id for tournaments
     * @param text the text that id is going to bee created for
     * @return resultWord
     */
    public static String removeSpaces(String text) {
        String[] splitWord = text.split(" ");
        String resultWord = "";
        for (String s : splitWord) {
            resultWord += s;
        }
        return resultWord;
    }

    /**
     * method that finds the id of a tournament
     * @param tournamentName tournament that we want to find ID for
     * @return ID as an int
     */
    public static int findID(String tournamentName) {
        return HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().stream().filter(t -> removeSpaces(t.getTournamentName()).equalsIgnoreCase(tournamentName)).collect(Collectors.toList()).get(0).getTournamentID();

    }
}
