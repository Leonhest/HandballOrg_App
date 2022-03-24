package edu.ntnu.idatt1002.g106.handballapp.mvp.backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is a class which creates the structure of tournament. Therefore, it contains all the relevant fields and methods
 * which define a tournament. These methods include storage and manipulations of matches.
 */
public class Tournament {
    private int tournamentID;
    private List<Match> tournament;
    private LocalDate startDate;
    private LocalDate endDate;
    private TeamRegister teamRegister;
    private Results results;
    private String layout;
    private String tournamentPlace;
    private int numFields;
    private int numTeams;

    /**
     * This is a constructor which allows for a tournament object to be initialized. The constructor gathers the vital
     * information for structuring a tournament.
     * @param tournamentID The identification number for a tournament, represented as an int
     * @param startDate    The date for which the tournament starts, represented as a LocalDate
     * @param endDate      The date for which the tournament ends, represented as a LocalDate
     */
    public Tournament(int tournamentID, LocalDate startDate, LocalDate endDate, String layout, String tournamentPlace, int numFields, int numTeams) throws IllegalArgumentException{
        if(tournamentID < 0) throw new IllegalArgumentException("The tournament ID cannot be less than 0");
        this.tournamentID = tournamentID;
        this.tournament = new ArrayList<>();
        this.teamRegister = new TeamRegister();
        this.startDate = startDate;
        this.endDate = endDate;
        this.results = new Results();
        this.layout = layout;
        this.tournamentPlace = tournamentPlace;
        this.numFields = numFields;
        this.numTeams = numTeams;
    }
    /**
     * This method adds a match to the list of matches
     * @param match Match to be added
     * @return      Status whether the match was added, if it's already in the list it returns false, if it's added true
     */
    public boolean addMatch(Match match){
        if(tournament.contains(match) || match == null){
            return false;
        }
        return tournament.add(match);
    }
    /**
     * This method removes a match from the list based on a given matchID
     * @param matchID A match's identification number, represented as an int
     * @return        Status on whether it was successfully removed, true if it was, false if it wasn't
     */
    public boolean removeMatchByMatchID(int matchID){
        return tournament.removeIf(m -> m.getMatchID() == matchID);
    }
    /**
     * This method retrieves the tournament list of matches.
     * @return List of matches registered in the tournament
     */
    public List<Match> getTournament() {
        return tournament;
    }

    /**
     * This method retrieves the tournament's identification number
     * @return Tournament's identification number, represented as an int
     */
    public int getTournamentID() {
        return tournamentID;
    }

    /**
     * This method retrieves the tournament's start date.
     * @return The start date of the tournament, represented as a LocalDate object
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * This method retrieves the tournament's end date.
     * @return The end date of the tournament, represented as a LocalDate object
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * This method retrieves the team register of the tournament.
     * @return Team register object over tournament
     */
    public TeamRegister getTeamRegister() {
        return teamRegister;
    }

    /**
     * This method retrieves the results of the tournament.
     * @return Results object of the tournament
     */
    public Results getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "tournament=" + tournament +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", participatingTeams=" +
                '}';
    }
}
