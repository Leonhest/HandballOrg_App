package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class SwitchScene {

    static String currentRegion = "";

    /**
     * a method that switches between screens/scenes in the application
     * @param location the scene you want to switch to
     * @param actionEvent the event when the button was pressed
     * @throws IOException when path not found
     */
    public static void switchScene(String location, ActionEvent actionEvent) throws IOException {
        Parent viewPage = FXMLLoader.load(Objects.requireNonNull(SwitchScene.class.getResource("/edu/ntnu/idatt1002/g106/handballapp/finalprod/view/" + location + ".fxml")));
        Scene page = new Scene(viewPage);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(page);
        window.show();
    }

    public static void setCurrentRegion(String region) throws IOException{
        currentRegion = region;
    }

    public static String getCurrentRegion() {
        return currentRegion;
    }
}
