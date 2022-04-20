package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import java.util.Objects;

/**
 * This class creates a general structure for a handball team. It, therefore, contains essential attributes and methods
 * for altering and retrieving the statistics.
 * @author Gruppe 6
 */
public class Team {
    private String teamName;
    private String teamLeader;
    private final String region;
    private int numPlayers;
    private int telephoneNum;
    private int totGoals = 0;
    private int totWins = 0;
    private int totLosses = 0;

    /**
     * This is a constructor allows for a Team object to be initialized with the  information intrinsic to a handball team.
     * @param teamName     Name of the team, represented as a String
     * @param teamLeader   Name of the team leader, represented as a String
     * @param region       The region where the team is from, represented as a String
     * @param numPlayers   Number of players, represented as an int
     * @param telephoneNum Phone number attached to the team, represented as an int
     */
    public Team(String teamName, String teamLeader, String region, int numPlayers, int telephoneNum) {
        if(numPlayers < 7) throw new IllegalArgumentException("Number of players cannot be negative");
        this.teamName = teamName;
        this.teamLeader = teamLeader;
        this.region = region;
        this.numPlayers = numPlayers;
        this.telephoneNum = telephoneNum;
    }

    /**
     * This method retrieves the team name.
     * @return Team name, represented as a String
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * This method changes the teamName of the object to the input String.
     * @param teamName The new team name, represented as a String
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * This method retrieves the team leader's name.
     * @return Team leader's name, represented as a String.
     */
    public String getTeamLeader() {
        return teamLeader;
    }

    /**
     * This method changes the team leader's name to the input.
     * @param teamLeader New name of the team leader, represented as a String
     */
    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    /**
     * This method retrieves the region the team is from.
     * @return The region the team is from, represented as a String.
     */
    public String getRegion() {
        return region;
    }

    /**
     * This method retrieves the number of players on the team.
     * @return Number of players on the team, represented as an int
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * This method changes the number of players on a team to the given input.
     * @param numPlayers New number of players, represented as an int
     */
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     * This method retrieves the telephone number connected to the team.
     * @return Telephone number of the given team, represented as an int.
     */
    public int getTelephoneNum() {
        return telephoneNum;
    }

    /**
     * This method changes the team's telephone number to the given input.
     * @param telephoneNum New telephone number, represented as an int
     */
    public void setTelephoneNum(int telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    /**
     * This method retrieves the total amount of goals scored by a team.
     * @return Total number of goals scored, represented as an int
     */
    public int getTotGoals() {
        return totGoals;
    }

    /**
     * This method changes the total number of goals scored by a team to the given input.
     * @param totGoals New total amount of goals, given as an int
     */
    public void setTotGoals(int totGoals) {
        this.totGoals = totGoals;
    }

    /**
     * This method adds the given number of goals to the total goals scored
     * @param goalsToAdd Goals to add, represented as an int
     */
    public void addTotGoals(int goalsToAdd){
        this.totGoals += goalsToAdd;
    }

    /**
     * This method retrieves the team's total amount of victories.
     * @return Total number of wins, represented as an int
     */
    public int getTotWins() {
        return totWins;
    }

    /**
     * This method changes the total amount of wins a team has to the given input.
     * @param totWins New total amount of wins, represented as an int
     */
    public void setTotWins(int totWins) {
        this.totWins = totWins;
    }

    /**
     * This method adds the given number of wins to the total wins.
     * @param winsToAdd Wins to add, given as an int
     */
    public void addTotWins(int winsToAdd){
        this.totWins += winsToAdd;
    }

    /**
     * This method retrieves the total amount of losses a team has faced.
     * @return Total amount of losses, represented as an int
     */
    public int getTotLosses() {
        return totLosses;
    }

    /**
     * This method changes the total amount of losses of a team to the given input.
     * @param totLosses New total amount of losses, represented as an int
     */
    public void setTotLosses(int totLosses) {
        this.totLosses = totLosses;
    }

    /**
     * This method adds the given number of losses to the total losses.
     * @param lossesToAdd Losses to add, given as an int
     */
    public void addTotLosses(int lossesToAdd){
        this.totLosses += lossesToAdd;
    }

    /**
     * toString method for returning all team information
     * @return all team information as a String
     */
    @Override
    public String toString() {
        return "*******" + teamName + "*******\n" +
                "Team Leader:" + teamLeader + '\n' +
                "Region: " + region + '\n' +
                "Number of Players: " + numPlayers + '\n' +
                "Phone number: " + telephoneNum + '\n' +
                "Total goals scored: " + totGoals + '\n' +
                "Total wins: " + totWins + '\n' +
                "Total losses: " + totLosses;
    }

    /**
     * method for checking if a team object equals another
     * @param o the checking object
     * @return true or false based on the result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team team)) return false;

        if (numPlayers != team.numPlayers) return false;
        if (telephoneNum != team.telephoneNum) return false;
        if (totGoals != team.totGoals) return false;
        if (totWins != team.totWins) return false;
        if (totLosses != team.totLosses) return false;
        if (!Objects.equals(teamName, team.teamName)) return false;
        if (!Objects.equals(teamLeader, team.teamLeader)) return false;
        return Objects.equals(region, team.region);
    }

    /**
     * hashMethod that hashes all teams
     * @return hashcode for team
     */
    @Override
    public int hashCode() {
        int result = teamName != null ? teamName.hashCode() : 0;
        result = 31 * result + (teamLeader != null ? teamLeader.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + numPlayers;
        result = 31 * result + telephoneNum;
        result = 31 * result + totGoals;
        result = 31 * result + totWins;
        result = 31 * result + totLosses;
        return result;
    }
}
