package edu.ntnu.idatt1002.g106.handballapp.mvp.backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class SwitchScene {

    public SwitchScene() {
    }

    public static void switchScene(String location, ActionEvent actionEvent) throws IOException {
        Parent viewPage = FXMLLoader.load(Objects.requireNonNull(SwitchScene.class.getResource("/edu/ntnu/idatt1002/g106/handballapp/mvp/view/" + location + ".fxml")));
        Scene page = new Scene(viewPage);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(page);
        window.show();
    }
}
