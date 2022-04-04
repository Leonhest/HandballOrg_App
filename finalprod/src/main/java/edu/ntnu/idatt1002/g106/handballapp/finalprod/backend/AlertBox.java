package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AlertBox {

    /**
     * method used when logOut button pressed
     * @return an alertbox with some information
     */
    public static boolean logOut() {
        boolean logoutConfirm = false;
        AnchorPane scenePane = new AnchorPane();
        Stage stage;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save before exiting");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("You successfully logged out!");
            logoutConfirm = true;
            stage.close();
        }
        return logoutConfirm;
    }

    //TODO: More methods to come
}

