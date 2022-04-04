package edu.ntnu.idatt1002.g106.handballapp.mvp.backend;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;


public class AlertBox {

    /**
     * method used when logOut button pressed
     * @return an alertbox with some information
     */
    public static int logOut() {
        AtomicInteger answer = new AtomicInteger();
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("LogOut");
        window.setMinWidth(250);
        Label label = new Label();
        label.setText("Do you want to log out");

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer.set(1);
            window.close();
        });
        noButton.setOnAction(e -> {
            answer.set(-1);
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return answer.get();
    }

    //TODO: More methods to come
}

