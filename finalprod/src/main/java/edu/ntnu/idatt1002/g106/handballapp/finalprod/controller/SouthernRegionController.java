package edu.ntnu.idatt1002.g106.handballapp.finalprod.controller;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.AlertBox;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.SwitchScene;;
import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.Tournament;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * this class is used for east region, and its methods
 * it holds the tournament list and is new tournament button
 * @author Gruppe 6.
 */
public class SouthernRegionController implements Initializable {

    @FXML
    private TableColumn<Tournament, Integer> startDateColumn;
    @FXML
    private TableColumn<Tournament, Integer> endDateColumn;
    @FXML
    private TableColumn<Tournament, String> tournamentNameColumn;
    @FXML
    private javafx.scene.control.TableView<Tournament> tableView;

    private AtomicInteger currentTournamentId = new AtomicInteger();

    /**
     * {@inheritDoc}
     * in addition, this method also keeps track if anythin in tableview is selected or click on, and will therefore
     * send the user to that tournament when selected
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentTournamentId = new AtomicInteger();

        tableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
                Tournament selectedTournament = tableView.getSelectionModel().getSelectedItem();
                HandballApplication.adminList.get(0).getTournamentRegister().getTournaments()
                        .forEach(t -> {
                            if (t.getTournamentName().equals(selectedTournament.getTournamentName())
                                    && t.getRegion().equals(SwitchScene.getCurrentRegion())){
                                currentTournamentId.set(t.getTournamentID());

                                try {
                                    goToCurrentPage(event);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
            }
        });

        updateList();
    }

    /**
     * take the user to the current page when click twice
     * @param event mouse event
     * @throws IOException when path not found
     */
    public void goToCurrentPage(Event event) throws IOException {
        HandballApplication.setChosenTournament(currentTournamentId.intValue());
        SwitchScene.switchScene("MainPage", event);
    }

    /**
     * this button takes the user back to region selection page
     * @param event any event
     * @throws IOException when path not found
     */
    public void backToRegionChoice(ActionEvent event) throws IOException {
        SwitchScene.switchScene("RegionChoice", event);
    }

    /**
     * log out method
     */
    public void logOutMethod() {
        if (AlertBox.logOut() == 1){
            System.exit(-1);
        }
    }

    /**
     * this method updates the tableview when new tournament in a specific region is created
     */
    @FXML
    private void updateList(){
        tournamentNameColumn.setCellValueFactory(new PropertyValueFactory<Tournament, String>("tournamentName"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<Tournament, Integer>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<Tournament, Integer>("endDate"));

        tableView.setItems(FXCollections.observableArrayList(
                HandballApplication.adminList.get(0).getTournamentRegister().getTournaments().stream()
                        .filter(t -> t.getRegion().equals("SouthernRegion")).toList()));

        tableView.refresh();
    }

    /**
     * this method tkes the user to StUpTournament page.
     * where they can create a new tournament
     * @param event any event
     * @throws IOException when path not found
     */
    @FXML
    public void toSetUpTournament(ActionEvent event) throws IOException {
        SwitchScene.switchScene("SetUpTournament", event);
    }

    /**
     * this method works for to selected tournament button, and is mostly used for blind people
     * who have a hard time navigating to tournaments with a mouse.
     * @param event any event
     * @throws IOException when path not found
     */
    @FXML
    public void toSelectedTournament(ActionEvent event) throws IOException {
        HandballApplication.setChosenTournament(currentTournamentId.intValue());
        SwitchScene.switchScene("MainPage", event);
    }
}
