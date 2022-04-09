package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.Tournament;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;

import javax.swing.text.TabableView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * class for handling events in a regional page
 * @author Gruppe 6
 */
public class RegionController  implements Initializable {

    @FXML
    private TableColumn<Tournament, Integer> startDateColumn;

    @FXML
    private TableColumn<Tournament, Integer> endDateColumn;

    @FXML
    private TableColumn<Tournament, String> tournamentNameColumn;

    @FXML
    private javafx.scene.control.TableView<Tournament> tableView;

    @FXML
    private Label regionHeader;

    @FXML
    private AtomicInteger currentTournamentId = new AtomicInteger();

    @FXML
    private Tournament selectedTournament;

    /**
     * {@inheritDoc}
     * in addition, this method also keeps track if anythin in tableview is selected or click on, and will therefore
     * send the user to that tournament when selected
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        regionHeader.setText(HandballApplication.chosenRegion.getRegionTxt());

        tableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
                selectedTournament = tableView.getSelectionModel().getSelectedItem();
                HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                        .forEach(t -> {
                            if (t.getTournamentName().equals(selectedTournament.getTournamentName())
                                    && t.getRegion().equals(HandballApplication.chosenRegion.getRegionTxt())){
                                currentTournamentId.set(t.getTournamentID());

                                try {
                                    goToCurrentPage(event);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
            }else if (tableView.getSelectionModel().getSelectedItem() == null){
                AlertBox.alertError("No tournament selected");
            }
        });


        updateList();
    }

    @FXML
    private void updateList(){
        tournamentNameColumn.setCellValueFactory(new PropertyValueFactory<Tournament, String>("tournamentName"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<Tournament, Integer>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<Tournament, Integer>("endDate"));

        tableView.setItems(FXCollections.observableArrayList(
                HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().stream()
                        .filter(t -> t.getRegion().equals(HandballApplication.chosenRegion.getRegionTxt())).toList()));

        tableView.refresh();
    }

    @FXML
    public void goToCurrentPage(Event event) throws IOException {
        HandballApplication.setChosenTournament(currentTournamentId.intValue());
        SwitchScene.switchScene("MainPage", event);
    }

    @FXML
    public void toSelectedTournament(ActionEvent event) throws IOException{
        if(tableView.getSelectionModel().getSelectedItem() == null){
            AlertBox.alertError("No tournament selected");
            return;
        }
        HandballApplication.setChosenTournament(tableView.getSelectionModel().getSelectedItem().getTournamentID());
        SwitchScene.switchScene("MainPage", event);
    }

    @FXML
    public void backToRegionChoice(ActionEvent event) throws IOException {
        SwitchScene.switchScene("RegionChoice", event);
    }

    /**
     * this method tkes the user to StUpTournament page.
     * where they can create a new tournament
     * @param event any event
     * @throws IOException when path not found
     */
    @FXML
    public void toSetUpNewTournament(ActionEvent event) throws IOException {
        SwitchScene.switchScene("SetUpTournament", event);
    }

    @FXML
    public void logOutMethod() {
        if (AlertBox.logOut() == 1){
            System.exit(-1);
        }
    }
}
