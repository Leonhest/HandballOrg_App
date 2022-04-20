package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * this class is used for the helpPage, send the user to different websides
 * the user can report a problem as well
 * @author Gruppe 6
 */
public class HelpPageController implements Initializable {

    @FXML private TextArea questionText;

    /**
     * {@inheritDoc}
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * method for loging out
     */
    @FXML
    public void logoutMethod() {
        if (AlertBox.logOut() == 1){
            System.exit(-1);
        }
    }

    /**
     * this method takes you to main page
     * @param event any event
     * @throws IOException when path not found
     */
    @FXML
    public void toMainPage(ActionEvent event) throws IOException {
        SwitchScene.switchScene("MainPage", event);
    }

    /**
     * this method send a question to us the programmers
     * @throws IOException when web not found
     */
    @FXML
    public void sendQuestion() throws IOException {
        String question = questionText.getText();
        //sends the string to a file in Wiki page
        URL sendMessage = new URL("https://gitlab.stud.idi.ntnu.no/G1-06/idatt-1002-2022-1-06/-/wikis/home");
        File QnAFile = new File(sendMessage.getFile());

        Writer wrt = new FileWriter(QnAFile);
        BufferedWriter bwr = new BufferedWriter(wrt);
        bwr.write(question);
        bwr.close();
        wrt.close();
    }
}
