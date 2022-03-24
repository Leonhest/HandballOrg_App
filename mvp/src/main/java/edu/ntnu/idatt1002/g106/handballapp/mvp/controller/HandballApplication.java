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

    public static void main(String[] args) {
        startData();
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/SetUpMatches.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Tournament");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception{
        super.init();
    }

    @Override
    public void stop() throws Exception{
        super.stop();
    }

    private static void startData(){
        Administrator admin = new Administrator(new User("Leon", "Hest", "123", "Leon.hesthaug@gmail.com"));
        adminList.add(admin);

//        admin.getTournamentRegister().addTournament(new Tournament(1, LocalDate.now(), LocalDate.now().plusDays(2)));
    }
}
