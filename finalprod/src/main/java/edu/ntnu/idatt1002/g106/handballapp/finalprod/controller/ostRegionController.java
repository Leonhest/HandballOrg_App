package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.Tournament;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ostRegionController implements Initializable {

    @FXML private javafx.scene.control.ListView<String> ListView;

    public List<String> tournamentString = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateList();
    }

    public void backToRegionChoice(ActionEvent event) throws IOException {
        SwitchScene.switchScene("RegionChoice", event);
    }

    public void logOutMethod(ActionEvent event) {
        if (AlertBox.logOut() == 1){
            System.exit(-1);
        }
    }

    @FXML
    private void updateList(){
        tournamentString = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                .stream()
                .filter(t -> t.getRegion().equals("RegionOst"))
                .map(Tournament::tournamentStringToList).toList();

        ListView.setItems(FXCollections.observableArrayList(tournamentString));
        ListView.refresh();
    }

    public void toSetUpNewTournament(ActionEvent event) throws IOException {
        SwitchScene.switchScene("SetUpTournament", event);
    }
}
