package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class which creates the structure of tournament. Therefore, it contains all the relevant fields and methods
 * which define a tournament. These methods include storage and manipulations of matches.
 */
public class Tournament {
    private int tournamentID;
    private List<Match> matchList;
    private LocalDate startDate;
    private LocalDate endDate;
    private TeamRegister teamRegister;
    private Results results;
    private String layout;
    private String tournamentPlace;
    private int numFields;
    private int numTeams;
    private String tournamentName;
    private List<List<Team>> roundTeamList;
    private List<List<Match>> roundMatchList;

    /**
     * This is a constructor which allows for a tournament object to be initialized. The constructor gathers the vital
     * information for structuring a tournament.
     * @param tournamentID The identification number for a tournament, represented as an int
     * @param startDate    The date for which the tournament starts, represented as a LocalDate
     * @param endDate      The date for which the tournament ends, represented as a LocalDate
     */
    public Tournament(int tournamentID, String tournamentName, LocalDate startDate, LocalDate endDate, String layout,
                      String tournamentPlace, int numFields, int numTeams) throws IllegalArgumentException{
        if(tournamentID < 0) throw new IllegalArgumentException("The tournament ID cannot be less than 0");
        this.tournamentID = tournamentID;
        this.matchList = new ArrayList<>();
        this.teamRegister = new TeamRegister();
        if(endDate == null || startDate == null || tournamentName == null || tournamentPlace == null) throw new NullPointerException("Fill in all fields");
        this.startDate = startDate;
        if(endDate.isBefore(startDate)) throw new IllegalArgumentException("End date must be before start date");
        this.endDate = endDate;
        this.results = new Results();
        this.layout = layout;
        if(tournamentPlace.isBlank() || tournamentPlace.isEmpty()) throw new IllegalArgumentException("Tournament place is invalid!");
        this.tournamentPlace = tournamentPlace;
        this.numFields = numFields;
        this.numTeams = numTeams;
        if(tournamentName.isBlank() || tournamentName.isEmpty()) throw new IllegalArgumentException("Tournament name is invalid!");
        this.tournamentName = tournamentName;
        this.roundMatchList = new ArrayList<>();
        this.roundTeamList = new ArrayList<>();

        int totalDays = (int)(ChronoUnit.DAYS.between(startDate, endDate) + 1);
        if(getTimeBetweenMatches() < 1.5) throw new IllegalArgumentException("Not possible to arrange");
    }


    /**
     * This method fills out the MatchList for each round by assigning times for the whole tournament. No two
     * consecutive rounds will overlap.
     */
    public void generateTournament(){
        int matchID = 1;
        List<LocalDateTime> tempTime = makeSchedule();
        for(int i = 1; i <= checkAmountRounds(); i++){
            this.roundTeamList.add(new ArrayList<>());
            this.roundMatchList.add(new ArrayList<>());

            for(int j = 0; j < checkNumMatchesByRound(i); j++){
                    this.roundMatchList.get(i-1).add(new Match(tempTime.get(0), (j % numFields)+1, matchID, i));
                    matchID++;
                    tempTime.remove(0);
            }
            int numMatchesTemp = checkNumMatchesByRound(i);
            while((intervalTakenByRound(i) - numMatchesTemp) > 0){
                tempTime.remove(0);
                numMatchesTemp++;
            }
        }
        this.roundTeamList.get(0).addAll(teamRegister.getListTeams());
    }


    //This method can be optimized by allowing matches to start at 9 the next day
    /**
     * This method creates a list of equally spaced out times for the given start and end date based on the
     * number of fields and the restricted start time: 9:00-21:00.
     * @return A list of all the times for all the matches
     */
    public List<LocalDateTime> makeSchedule(){
        List<LocalDateTime> matchSchedule = new ArrayList<>();

        int timePerInterval = getTimeBetweenMatches();
        //TODO: if timePerInterval less than 1.5, throw error.
        int hours = 9 - timePerInterval;
        int minutes = 0;
        int days = 0;

        for(int i = 0; i < totalIntervalsNeeded(); i++){

            if((hours + timePerInterval) <= 21){
                hours += timePerInterval;
            }
            else{
                hours = (hours + timePerInterval) - 12;
                days++;
            }
            for(int j = 0; j < numFields; j++){
                matchSchedule.add(LocalDateTime.of(startDate.plusDays(days), LocalTime.of(hours, minutes)));
            }
        }
        return matchSchedule;

    }

    public int getTimeBetweenMatches(){
        int totalDays = (int)(ChronoUnit.DAYS.between(startDate, endDate) + 1);
        int totalTime = totalDays * 12;
        return totalTime / totalIntervalsNeeded();
    }

    /**
     * This method finds the total number of intervals of games needed in order to ensure that no two rounds overlap
     * while considering all fields.
     * @return The total amount of intervals needed to split the total tournament time, represented as an int
     */
    public int totalIntervalsNeeded(){
        int totalIntervalNeeded = numTeams - 1;
        for(int i = 1; i <= checkAmountRounds(); i++){
            totalIntervalNeeded += (numFields - (checkNumMatchesByRound(i) % numFields));
        }
        return totalIntervalNeeded / numFields;
    }

    //This method could be a helper for the one above
    public int intervalTakenByRound(int round){
        return checkNumMatchesByRound(round) + (numFields - (checkNumMatchesByRound(round) % numFields));
    }

    /**
     * This method returns the amount of matches in the given round
     * @param round The current round, represented as an int
     * @return      Number of matches in the round, represented as an int
     */
    public int checkNumMatchesByRound(int round){
        return (int) (numTeams/(Math.pow(2, round)));
    }

    /**
     * This method checks how many rounds will exist in the given tournament.
     * @return Number of rounds, represented as an int
     */
    public int checkAmountRounds(){
        return (int) (Math.log(numTeams)/Math.log(2));
    }


//    /**
//     * This method adds all the winning teams to matches for the next round.
//     * @param round The current round of the tournament, represented as an int
//     */
//    public void generateRoundWithTeams(int round){
//        if(this.roundTeamList.get(round-1).size() != checkNumMatchesByRound(round) * 2) return;
//        for(int i = 0; i < roundTeamList.get(round-1).size(); i+=2 ){
//            this.roundMatchList.get(round-1).add(new Match())
//        }
//    }

    /**
     * getMethod that gets Tournament Name
     * @return tournament name as a String
     */
    public String getTournamentName() {
        return tournamentName;
    }

    /**
     * This method adds a match to the list of matches
     * @param match Match to be added
     * @return      Status whether the match was added, if it's already in the list it returns false, if it's added true
     */
    public boolean addMatch(Match match){
        if(matchList.contains(match) || match == null){
            return false;
        }
        return matchList.add(match);
    }
    /**
     * This method removes a match from the list based on a given matchID
     * @param matchID A match's identification number, represented as an int
     * @return        Status on whether it was successfully removed, true if it was, false if it wasn't
     */
    public boolean removeMatchByMatchID(int matchID){
        return matchList.removeIf(m -> m.getMatchID() == matchID);
    }
    /**
     * This method retrieves the tournament list of matches.
     * @return List of matches registered in the tournament
     */
    public List<Match> getMatchList() {
        return matchList;
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

    public List<List<Team>> getRoundTeamList() {
        return roundTeamList;
    }

    public List<List<Match>> getRoundMatchList() {
        return roundMatchList;
    }

    /**
     * toString method for returning all Tournament information
     * @return all Tournament information as a String
     */
    @Override
    public String toString() {
        return "Tournament{" +
                "tournament=" + matchList +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", participatingTeams=" +
                '}';
    }
}
