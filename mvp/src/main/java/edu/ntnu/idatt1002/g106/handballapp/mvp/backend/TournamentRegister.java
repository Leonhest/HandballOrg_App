package edu.ntnu.idatt1002.g106.handballapp.mvp.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class serves as the structure of a tournament register. Therefore, at its core, it works with manipulating a
 * list of Tournament objects.
 */
public class TournamentRegister {
    private List<Tournament> tournaments = new ArrayList<>();

    /**
     * This method adds a tournament to the register.
     * @param tournament The tournament to be added to the register
     * @return           Whether the tournament was added successfully to the list or not, true if it was, false if not
     */
    public boolean addTournament(Tournament tournament){
        Objects.requireNonNull(tournament);
        for(Tournament i: tournaments){
            if(i.getTournamentID() == tournament.getTournamentID())
                return false;
        }
        tournaments.add(tournament);
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
