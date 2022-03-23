package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HandballApplication extends Application{

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/SetUpTournament.fxml"));
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
}
