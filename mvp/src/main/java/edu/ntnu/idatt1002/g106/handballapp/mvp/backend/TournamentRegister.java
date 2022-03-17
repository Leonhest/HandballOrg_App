package edu.ntnu.idatt1002.g106.handballapp.mvp.backend;

import java.util.List;

/**
 * This class serves as the structure of a tournament register. Therefore, at its core, it works with manipulating a
 * list of Tournament objects.
 */
public class TournamentRegister {
    private List<Tournament> tournaments;

    /**
     * This method adds a tournament to the register.
     * @param tournament The tournament to be added to the register
     * @return           Whether the tournament was added successfully to the list or not, true if it was, false if not
     */
    public boolean addTournament(Tournament tournament){
        if(tournaments.contains(tournament)){
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
