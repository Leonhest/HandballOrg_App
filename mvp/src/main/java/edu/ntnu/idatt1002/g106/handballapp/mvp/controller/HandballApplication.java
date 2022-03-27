package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HandballApplication extends Application{

    //todo: tilgangsmodifikator?
    static List<Administrator> adminList = new ArrayList<>();
    static int chosenTournament = -1;
    //static Tournament tournament= HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(HandballApplication.chosenTournament);//todo: taken in use - can simplify much code


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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/FrontPage.fxml"));
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
     * method for all stating hardcoded data
     */
    private static void startData(){
        Administrator admin = new Administrator(new User("Leon", "Hest", "123", "Leon.hesthaug@gmail.com"));
        adminList.add(admin);

        Tournament oslo = new Tournament(0, "Oslo Tournament",LocalDate.now(), LocalDate.now().plusDays(2), "layout1", "Asker", 3, 16);
        Tournament trondheim = new Tournament(1, "Trondheim Tournament",LocalDate.now(), LocalDate.now().plusDays(7), "layout1", "Trondheim", 3, 8);
        Tournament asker = new Tournament(2, "Asker Tournament",LocalDate.now(), LocalDate.now().plusDays(5), "layout2", "Oslo", 2, 8);

        Tournament[] tournaments = {oslo, trondheim, asker};

        //admin.getTournamentRegister().getTournaments().get(chosenTournament).getTeamRegister().addTeam(new Team("Asker Lions", "Leon Hest", "Asker", 12, 94506769));

        for (int i = 0; i < tournaments.length; i++) {
            tournaments[i].getTeamRegister().addTeam(new Team("Asker Lions", "Leon Hest", "Asker", 12, 94506769));
            tournaments[i].getTeamRegister().addTeam(new Team("Oslo Eagles", "Tore Tang", "Oslo", 14, 94503269));
            tournaments[i].getTeamRegister().addTeam(new Team("Trondheim Sharks", "Hans", "Tronheim", 11, 94523769));
            tournaments[i].getTeamRegister().addTeam(new Team("Drammen Rats", "Trym", "Drammen", 9, 94506734));
            tournaments[i].getTeamRegister().addTeam(new Team("Bergen Bulls", "Eirik", "Bergen", 10, 94505649));

            admin.getTournamentRegister().addTournament(tournaments[i]);

            tournaments[i].addMatch(new Match(LocalDateTime.of(2022,
                    4, 14, 9, 0), 1, admin.getTournamentRegister().getTournaments().get(i).getTeamRegister()
                    .findTeamBasedOnTeamName("Asker Lions"), admin.getTournamentRegister().getTournaments().get(i)
                    .getTeamRegister().findTeamBasedOnTeamName("Bergen Bulls"), 1, 3));

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
     * method that removes spaces when needed, used for creating an id for tournaments
     * @param text the text that id is going to bee created for
     * @return resultWord
     */
    public static String removeSpaces(String text) {
        String[] splitWord = text.split(" ");
        String resultWord = "";
        for (int i = 0; i < splitWord.length; i++) {
            resultWord += splitWord[i];
        }
        return resultWord;
    }

    /**
     * method that finds the id of a tournament
     * @param tournamentName tournament that we want to find ID for
     * @return ID as an int
     */
    public static int findID(String tournamentName) {
        //todo: remove comments when sure of code
        //System.out.println("Selected button (EqulasIgnoreCase) " + tournamentName);
        //System.out.println("List (EqulasIgnoreCase) " + removeSpaces(HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().get(0).getTournamentName()));
        //System.out.println("Size " + HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().stream().filter(t -> removeSpaces(t.getTournamentName()).equalsIgnoreCase(tournamentName)).collect(Collectors.toList()).size());
        return HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().stream().filter(t -> removeSpaces(t.getTournamentName()).equalsIgnoreCase(tournamentName)).collect(Collectors.toList()).get(0).getTournamentID();

    }
}
