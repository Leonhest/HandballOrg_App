package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a match in a tournament.
 * @author Gruppe 6
 */
public class Match implements Serializable {

    private Map<String, Integer> teamScore;
    private LocalDateTime startTime;
    //Fields?
    private int numField;
    private int matchID;
    private int roundNum;
    private Team team1;
    private Team team2;

    /**
     * Initializes a new Match object with necessary variables.
     *
     * @param startTime     Start time of the match as LocalTime
     * @param roundNum      Round the match is in as int
     * @param team1         First Team
     * @param team2         Second Team
     * @param matchID       Identifier of the match
     * @param numField      Number of the field to be played on
     */
    public Match(LocalDateTime startTime, int roundNum, Team team1, Team team2, int matchID, int numField) throws IllegalArgumentException, NullPointerException{
        Objects.requireNonNull(team1);
        Objects.requireNonNull(team2);
        if(matchID < 0) throw new IllegalArgumentException("matchId cannot be negative");
        teamScore = new HashMap<>();
        this.startTime = startTime;
        this.roundNum = roundNum;
        this.team1 = team1;
        this.team2 = team2;
        this.teamScore.put(team1.getTeamName(), 0);
        this.teamScore.put(team2.getTeamName(), 0);
        this.matchID = matchID;
        this.numField = numField;
    }

    /**
     * the class constructor with all variables needed
     * @param startTime starting time
     * @param numField the fields number
     * @param matchID id of the match
     * @param roundNum round number for the match
     */
    public Match(LocalDateTime startTime, int numField, int matchID, int roundNum) {
        teamScore = new HashMap<>();
        this.startTime = startTime;
        this.numField = numField;
        if(matchID < 0) throw new IllegalArgumentException("matchId cannot be negative");
        this.matchID = matchID;
        this.roundNum = roundNum;
    }

    /**
     * This method adds two teams to the match
     * @param team1
     * @param team2
     */
    public void addTeam(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;
        this.teamScore.put(team1.getTeamName(), 0);
        this.teamScore.put(team2.getTeamName(), 0);
    }

    /**
     * This method checks if the team is in the match based on the team name
     * @param teamName Name of a team, represented as a String
     * @return         Status of whether match contains team
     */
    public boolean hasTeam(String teamName){
        return team1.getTeamName().equals(teamName) || team2.getTeamName().equals(teamName);
    }

    /**
     * a getMethod for getting the first team registered
     * @return team1 as a team object
     */
    public Team getTeam1() {
        return team1;
    }

    /**
     * a getMethod for getting the second team registered
     * @return team2 as a team object
     */
    public Team getTeam2() {
        return team2;
    }

    /**
     * Sets the score of a team during a match
     *
     * @param teamName  Team to change score
     * @param score     New score
     */
    //TODO: Exception handling for the different methods, for this one e.g. replace throws NULLPOINTEREXCEPTIOn if teamName doesnt exist
    public void setScore(String teamName, int score){
        if(score < 0) throw new IllegalArgumentException("Score cannot be negative");
        if(teamName.isBlank() || teamName.isEmpty()) throw new IllegalArgumentException("Name is invalid (empty or blank)");
        teamScore.replace(teamName, score);

    }

    /**
     * This method gets the score by looking at the team
     * @param teamName Name of the team, represented as a String
     * @return         Score of the given team, represented as an int
     */
    public int getScoreByTeamName(String teamName){
        if(teamName.isBlank() || teamName.isEmpty()) throw new IllegalArgumentException("Name is invalid (empty or blank)");
        return teamScore.get(teamName);
    }

    /**
     * Gets start time of match
     *
     * @return  match start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Gets field number
     *
     * @return field number
     */
    public int getNumField() {
        return numField;
    }

    /**
     * Gets match ID
     *
     * @return match ID
     */
    public int getMatchID() {
        return matchID;
    }

    /**
     * Gets round number
     * @return  round number
     */
    public int getRoundNum(){
        return roundNum;
    }

    /**
     * Gets final result of match
     *
     * @return  final results as String
     */
    public String getFinalResult(){
        return teamScore.get(team1.getTeamName()) + " - " + teamScore.get(team2.getTeamName());
    }

    /**
     * Gets winning team
     *
     * @return winning team as Team object
     */
    public Team getWinner(){
        if(this.teamScore.get(team1.getTeamName()) > this.teamScore.get(team2.getTeamName())){
            return team1;
        }
        else if(this.teamScore.get(team1.getTeamName()) < this.teamScore.get(team2.getTeamName())){
            return team2;
        }
        return null;
    }

    /**
     * This method returns the losing team of the given match
     * @return Team that lost
     */
    public Team getLoser(){
        if(this.teamScore.get(team1.getTeamName()) > this.teamScore.get(team2.getTeamName())){
            return team2;
        }
        else if(this.teamScore.get(team1.getTeamName()) < this.teamScore.get(team2.getTeamName())){
            return team1;
        }
        return null;
    }

    /**
     * a getMethod for getting teams names
     * @return teams names as a vs String format
     */
    public String getPlayers() {
        return team1.getTeamName() + " vs. " + team2.getTeamName();
    }

    /**
     * method for getting start time
     * @return getting start time
     */
    public LocalTime getTime() {
        return startTime.toLocalTime();
    }

    /**
     * toString method for returning all match information
     * @return all match information as a String
     */
    @Override
    public String toString() {
        return "***At " + startTime.toString() + " " + team1.toString() + " played against " + team2.toString() +
                "***\n The game ended with " + getFinalResult() + " and the winner was "
                + this.getWinner().toString();
    }
}
