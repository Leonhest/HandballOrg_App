package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * this class is a static class used for switching between the different pages/scenes in the program
 * @author Gruppe 6
 */
public class SwitchScene {

    /**
     * a method that switches between screens/scenes in the application
     * @param location the scene you want to switch to
     * @param event the event when the button was pressed
     * @throws IOException when path not found
     */
    public static void switchScene(String location, Event event) throws IOException {
        Parent viewPage = FXMLLoader.load(Objects.requireNonNull(SwitchScene.class.getResource("/edu/ntnu/idatt1002/g106" +
                "/handballapp/finalprod/view/" + location + ".fxml")));

        Scene page = new Scene(viewPage);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(page);
        window.show();
    }
}
