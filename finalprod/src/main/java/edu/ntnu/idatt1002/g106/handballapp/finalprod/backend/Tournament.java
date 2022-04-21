package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This is a class which creates the structure of tournament. Therefore, it contains all the relevant fields and methods
 * which define a tournament. These methods include storage and manipulations of matches.
 * @author Gruppe 6
 */
public class Tournament implements Serializable {
    private int tournamentID;
    private List<Match> matchList;
    private LocalDate startDate;
    private LocalDate endDate;
    private TeamRegister teamRegister;
    private Results results;
    private String tournamentPlace;
    private int numFields;
    private int numTeams;
    private String tournamentName;
    private List<List<Team>> roundTeamList;
    private List<List<Match>> roundMatchList;
    private int currentRound = 1;
    private String region;//todo: if time find region hence on tournamentPlace

    /**
     * This is a constructor which allows for a tournament object to be initialized. The constructor gathers the vital
     * information for structuring a tournament.
     * @param tournamentID The identification number for a tournament, represented as an int
     * @param startDate    The date for which the tournament starts, represented as a LocalDate
     * @param endDate      The date for which the tournament ends, represented as a LocalDate
     */
    public Tournament(int tournamentID, String tournamentName, LocalDate startDate, LocalDate endDate,
                      String tournamentPlace, int numFields, int numTeams, String region) throws IllegalArgumentException{
        if(tournamentID < 0) throw new IllegalArgumentException("The tournament ID cannot be less than 0");
        this.tournamentID = tournamentID;
        this.matchList = new ArrayList<>();
        this.teamRegister = new TeamRegister();
        if(endDate == null || startDate == null || tournamentName == null || tournamentPlace == null) throw new NullPointerException("Fill in all fields");
        if (startDate.isBefore(LocalDate.now())) throw new IllegalArgumentException("Start date must be before current start date");
        this.startDate = startDate;
        if(endDate.isBefore(startDate)) throw new IllegalArgumentException("End date must be after start date");
        this.endDate = endDate;
        this.results = new Results();
        if(tournamentPlace.isBlank() || tournamentPlace.isEmpty()) throw new IllegalArgumentException("Tournament place is invalid!");
        this.tournamentPlace = tournamentPlace;
        this.numFields = numFields;
        this.numTeams = numTeams;
        if(tournamentName.isBlank() || tournamentName.isEmpty()) throw new IllegalArgumentException("Tournament name is invalid!");
        this.tournamentName = tournamentName;
        this.region = region;
        this.roundMatchList = new ArrayList<>();
        this.roundTeamList = new ArrayList<>();

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
        //With this additional list, the last list will contain the winner of the whole tournament
        this.roundTeamList.add(new ArrayList<>());
    }


    /**
     * This method creates a list of equally spaced out times for the given start and end date based on the
     * number of fields and the restricted start time: 9:00-21:00.
     * @return A list of all the times for all the matches
     */
    public List<LocalDateTime> makeSchedule(){
        List<LocalDateTime> matchSchedule = new ArrayList<>();

        double timePerInterval = getTimeBetweenMatches();
        double hours = 9 - timePerInterval;
        double minutes = 0;
        int days = 0;

        for(int i = 0; i < totalIntervalsNeeded(); i++){

            if((hours + timePerInterval) < 21 ||((hours + timePerInterval) == 21 && minutes == 0)){
                hours += timePerInterval;
                minutes = ((hours % 1) * 60);
            }
            else{
                hours = (hours + timePerInterval) - 12;
                minutes = ((hours % 1) * 60);
                days++;
            }
            for(int j = 0; j < numFields; j++){
                matchSchedule.add(LocalDateTime.of(startDate.plusDays(days), LocalTime.of((int) Math.floor(hours), (int) Math.round(minutes))));
            }
        }
        return matchSchedule;
    }

    /**
     * This method gets the amount of time needed between matches
     * @return Time between matches in tournament, represented by double
     */
    public double getTimeBetweenMatches(){
        int totalDays = (int)(ChronoUnit.DAYS.between(startDate, endDate) + 1);
        double totalTime = totalDays * 12;
        return totalTime / totalIntervalsNeeded();
    }

    /**
     * If we want times for matches that start at exactly hh:00, then this method is used in make schedule instead of
     * the one above.
     * @return A rounded time interval between matches, represented as an int
     */
    public int getTimeBetweenMatchesRounded(){
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
        int totalIntervalNeeded = 0;
        for(int i = 1; i <= checkAmountRounds(); i++){
            totalIntervalNeeded += intervalTakenByRound(i);
        }
        return totalIntervalNeeded / numFields;
    }

    /**
     * This method checks the intervals taken by a given round
     * @param round The given round, represented as an int
     * @return      The interval take by a given round
     */
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


    /**
     * This method adds all the winning teams to matches for the next round.
     * @param round The current round of the tournament, represented as an int
     */
    public void generateRoundWithTeams(int round) throws IllegalArgumentException{
        if(this.teamRegister.getListTeams().size() == numTeams && this.roundTeamList.get(0).size() != numTeams) setFirstTeamsList();
        if(this.teamRegister.getListTeams().size() != numTeams) throw new IllegalArgumentException("Not enough teams entered! "
                + (numTeams - this.teamRegister.getListTeams().size()) + " more teams need to be entered.");
        if(currentRound == this.checkAmountRounds() + 1) return;
        List<Team> tempList = new ArrayList<>();
        tempList.addAll(this.roundTeamList.get(round-1));
        if(round != 1 && this.roundTeamList.get(round-1).size() != checkNumMatchesByRound(round)*2){
            throw new IllegalArgumentException("All the winners from the previous round haven't been registered yet");
        }

        for(int i = 0; i < this.roundMatchList.get(round-1).size(); i++){
                this.roundMatchList.get(round-1).get(i).addTeam(tempList.get(0), tempList.get(1));
                this.matchList.add(this.roundMatchList.get(round-1).get(i));
                tempList.remove(0);
                tempList.remove(0);
        }
       currentRound++;
    }

    /**
     * This method updates all the teams in the tournament's information.
     */
    public void updateAllTeamsInTournament(){
        for(Team team : teamRegister.getListTeams()){
            updateTeamInfoByName(team.getTeamName());
        }
    }

    /**
     * This method updates a given team's information (goals, wins, and losses).
     * @param teamName Name of team, represented as a String
     */
    public void updateTeamInfoByName(String teamName){
        setWinsOfTeamByName(teamName);
        setLosesOfTeamByName(teamName);
        setTotalGoalsOfTeamByName(teamName);
    }

    /**
     * This method sets the total wins of a team, during the tournament, based on each match played
     * @param teamName Name of the team, represented as a String
     */
    public void setWinsOfTeamByName(String teamName){
        AtomicInteger totalWinsOfTournament = new AtomicInteger();
        for(Team team : this.teamRegister.getListTeams()){
            if(team.getTeamName().equals(teamName)){
                this.matchList.stream().forEach(match -> {
                    if(match.hasTeam(teamName)){
                        if(match.getWinner() != null && match.getWinner().getTeamName().equals(teamName))totalWinsOfTournament.getAndIncrement();
                    }
                });
                team.setTotWins(totalWinsOfTournament.intValue());
            }
        }
    }

    /**
     * This method sets the total losses of a team, during the tournament, based on each match played
     * @param teamName Name of the team, represented as a String
     */
    public void setLosesOfTeamByName(String teamName){
        AtomicInteger totalLossesOfTournament = new AtomicInteger();
        for(Team team : this.teamRegister.getListTeams()){
            if(team.getTeamName().equals(teamName)){
                this.matchList.stream().forEach(match -> {
                    if(match.hasTeam(teamName)) {
                        if(match.getLoser() != null && match.getLoser().getTeamName().equals(teamName)) totalLossesOfTournament.getAndIncrement();
                    }
                });
                team.setTotLosses(totalLossesOfTournament.intValue());
            }
        }
    }

    /**
     * This method sets the total goals of a team, during the tournament, based on each match played
     * @param teamName Name of a team, represented as a String
     */
    public void setTotalGoalsOfTeamByName(String teamName){
        AtomicInteger totalGoalsOfTournament = new AtomicInteger();
        for(Team team : this.teamRegister.getListTeams()){
            if(team.getTeamName().equals(teamName)){
                this.matchList.stream().forEach(match -> {
                    if(match.hasTeam(teamName)) totalGoalsOfTournament.addAndGet(match.getScoreByTeamName(teamName));
                });
                team.setTotGoals(totalGoalsOfTournament.intValue());
            }
        }
    }

    /**
     * This method sets the first teams list full with the team register.
     */
    public void setFirstTeamsList(){
        this.roundTeamList.get(0).addAll(this.teamRegister.getListTeams());
    }

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

    //todo: remove?
    /**
     * This method retrieves the list of winning teams for each round.
     * @return List of winning teams, represented using Team objects
     */
    public List<List<Team>> getRoundTeamList() {
        return roundTeamList;
    }

    /**
     * This method returns the list of matches for each round.
     * @return List of Match objects for each round
     */
    public List<List<Match>> getRoundMatchList() {
        return roundMatchList;
    }

    /**
     * this method gets the current round of the tournament, used when generations of matches
     * @return int value of the current round
     */
    public int getCurrentRound(){
        return currentRound;
    }

    /**
     * get the number of remaining teams
     * @return in of remaining teams
     */
    public int getNumTeams(){
        return numTeams;
    }

    /**
     * gets the region of the tournament
     * @return string of the region
     */
    public String getRegion() {
        return region;
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

//    public String tournamentStringToList(){
//        return getTournamentName() +"\t\tStart date: "+getStartDate()+"\t\tEnd date:"+getEndDate();
//    }
}
