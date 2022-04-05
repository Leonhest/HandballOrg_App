package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import com.sun.jdi.IntegerValue;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.Tournament;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class nordRegionController implements Initializable {

    @FXML private javafx.scene.control.ListView<String> ListView;

    public List<String> tournamentString = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AtomicInteger currentTournamentId = new AtomicInteger();

        ListView.getSelectionModel().selectedItemProperty().addListener((a,b,selected) -> {
            HandballApplication.setChosenTournament(currentTournamentId.intValue());
        });

        ListView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY){
                String[] selectedTournamentList = ListView.getSelectionModel().getSelectedItem().split("\t\t");
                HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                        .forEach(t -> {
                            if (t.getTournamentName().equals(selectedTournamentList[0])){
                                System.out.println(t.getTournamentID());
                                currentTournamentId.set(t.getTournamentID());
                            }
                        });
            }
        });

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
                .filter(t -> t.getRegion().equals("RegionNord"))
                .map(Tournament::tournamentStringToList).toList();

        ListView.setItems(FXCollections.observableArrayList(tournamentString));
        ListView.refresh();
    }

    public void toSetUpNewTournament(ActionEvent event) throws IOException {
        SwitchScene.switchScene("SetUpTournament", event);
    }
}
