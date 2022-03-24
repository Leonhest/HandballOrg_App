package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Administrator;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.Tournament;
import edu.ntnu.idatt1002.g106.handballapp.mvp.backend.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HandballApplication extends Application{

    static List<Administrator> adminList = new ArrayList<>();
    static int chosenTournament = 0;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/FrontPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Tournament");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception{
        super.init();
        startData();
    }

    @Override
    public void stop() throws Exception{
        super.stop();
    }

    private static void startData(){
        Administrator admin = new Administrator(new User("Leon", "Hest", "123", "Leon.hesthaug@gmail.com"));
        admin.getTournamentRegister().addTournament(new Tournament(1, "Oslo open",LocalDate.now(), LocalDate.now().plusDays(2), "layout1", "Asker", 3, 16));
        admin.getTournamentRegister().addTournament(new Tournament(2, "Asker open",LocalDate.now(), LocalDate.now().plusDays(5), "layout2", "Oslo", 2, 8));
        admin.getTournamentRegister().addTournament(new Tournament(3, "Trondheim open",LocalDate.now(), LocalDate.now().plusDays(7), "layout1", "Trondheim", 3, 8));
        adminList.add(admin);
     }

    public static void setChosenTournament(int tournamentID){
        chosenTournament = tournamentID;
    }
}
