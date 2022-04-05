package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.Tournament;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class sorRegionController implements Initializable {

    @FXML private javafx.scene.control.ListView<String> ListView;

    public List<String> tournamentString = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateList();
    }

    @FXML
    public void backToRegionChoice(ActionEvent event) throws IOException {
        SwitchScene.switchScene("RegionChoice", event);
    }

    @FXML
    public void logOutMethod() {
        if (AlertBox.logOut() == 1){
            System.exit(-1);
        }
    }

    @FXML
    private void updateList(){
        tournamentString = HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                        .stream()
                                .filter(t -> t.getRegion().equals("RegionSor"))
                .map(Tournament::tournamentStringToList).toList();

            ListView.setItems(FXCollections.observableArrayList(tournamentString));
        ListView.refresh();
    }

    @FXML
    public void toSetUpTournament(ActionEvent event) throws IOException {
        SwitchScene.switchScene("SetUpTournament", event);
    }
}
