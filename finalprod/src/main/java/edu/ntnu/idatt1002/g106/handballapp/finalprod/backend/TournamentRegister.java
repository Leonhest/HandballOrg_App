package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This class serves as the structure of a tournament register. Therefore, at its core, it works with manipulating a
 * list of Tournament objects.
 * @author Gruppe 6
 */
public class TournamentRegister implements Serializable {

    private List<Tournament> tournaments = new ArrayList<>();

    /**
     * This method adds a tournament to the register.
     * @param tournament The tournament to be added to the register
     * @return           Whether the tournament was added successfully to the list or not, true if it was, false if not
     */
    public boolean addTournament(Tournament tournament){
        if(tournament == null) return false;
        for(Tournament i: tournaments) {
            if(i.getTournamentID() == tournament.getTournamentID())
                return false;
        }
        tournaments.add(tournament);
        return true;
    }

    /**
     * Method to add a list of tournaments to the TournamentRegister
     * @param tournaments List with tournaments
     * @return True if every tournament was added successfully, otherwise false
     */
    public boolean addListOfTournaments(List<Tournament> tournaments){
        for (Tournament tournament:tournaments) {
            if (!addTournament(tournament)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method retrieves the list of tournaments.
     * @return Tournament register, represented as a List.
     */
    public List<Tournament> getTournaments() {
        return tournaments;
    }
}
