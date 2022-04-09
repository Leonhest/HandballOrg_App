package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import java.util.*;

/**
 * This is a class forming the structure of a team register. It, therefore, has a map which allows teams
 * to be navigated easily. Additionally, it contains methods that allow for the manipulation of the teams
 * and the list.
 * @author Gruppe 6
 */
public class TeamRegister {

    private Map<String, Team> teams;

    public TeamRegister() {
        this.teams = new HashMap<>();
    }

    /**
     * This method adds a team to the map, as long as it doesn't already exist
     * @param team Team to be added to the list
     * @return     Status of the action, true if it successfully added the team, otherwise false
     */
    public boolean addTeam(Team team){
        if(team == null) return false;
        if(!teams.containsKey(team.getTeamName())){//todo: can be added to if statement above
            teams.put(team.getTeamName(), team);
            return true;
        }
        else return false;
    }

    /**
     * This method removes a team from the team register.
     * @param team The team object to be removed
     * @return     Status on whether it was successfully removed, true if it was, if not then false
     */
    public boolean removeTeam(Team team){
        if(teams.containsKey(team.getTeamName())){
            teams.remove(team.getTeamName());
            return true;
        }
        else return false;
    }

    /**
     * This method finds a team based on the team's name from the register.
     * @param teamName Team's name, represented as a String
     * @return         The team that has the given team name, null if non exist
     */
    public Team findTeamBasedOnTeamName(String teamName){
        return teams.get(teamName);
    }

    /**
     * method that registers a teams new number of wins
     * @param teamName
     * @param newTotWins new number of wins
     */
    public void editTotWins(String teamName, int newTotWins){
        if(newTotWins < 0) throw new IllegalArgumentException("Total wins cannot be negative");
        for(Team i: this.teams.values()){
            if(i.getTeamName().equals(teamName)){
                i.setTotWins(newTotWins);
            }
        }
    }

    /**
     * This method changes the number of total losses a given team has.
     * @param teamName     The team's name, represented as a String
     * @param newTotLosses The new number of losses, represented as an int
     */
    public void editTotLosses(String teamName, int newTotLosses){
        if(newTotLosses < 0) throw new IllegalArgumentException("Total losses cannot be negative");
        for(Team i: this.teams.values()){
            if(i.getTeamName().equals(teamName)){
                i.setTotLosses(newTotLosses);
            }
        }
    }

    /**
     * This method changes the total number of goals of a given team.
     * @param teamName     The team's name, represented as a String
     * @param newTotGoals  The new number of goals, represented as an int
     */
    public void editTotGoals(String teamName, int newTotGoals){
        if(newTotGoals < 0) throw new IllegalArgumentException("Total goals cannot be negative");
        for(Team i: this.teams.values()){
            if(i.getTeamName().equals(teamName)){
                i.setTotGoals(newTotGoals);
            }
        }
    }

    /**
     * This method changes the total number of players on a given team.
     * @param teamName      The team's name, represented as a String
     * @param newNumPlayers The new number of players, represented as an int
     */
    public void editNumPlayers(String teamName, int newNumPlayers){
        if(newNumPlayers < 0) throw new IllegalArgumentException("Number of players cannot be negative");
        for(Team i: this.teams.values()){
            if(i.getTeamName().equals(teamName)){
                i.setNumPlayers(newNumPlayers);
            }
        }
    }

    //todo: should be List?
    /**
     * This method retrieves the map of teams.
     * @return Map of teams
     */
    public Map<String, Team> getTeams() {
         return teams;
    }

    /**
     * method that gets a list of all teams
     * @return list with all teams
     */
    public List<Team> getListTeams() {
        Iterator<Team> teamIterator = teams.values().iterator();
        List<Team> teams = new ArrayList<>();
        while (teamIterator.hasNext()) {
            teams.add(teamIterator.next());
        }
        return teams;
    }

    /**
     * toString method for returning all TeamRegister information
     * @return all TeamRegister information as a String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("The following teams exist in the register: ");
        for(Team team: this.teams.values()){
            sb.append("\n").append(team.toString());
        }
        return sb.toString();
    }
}
