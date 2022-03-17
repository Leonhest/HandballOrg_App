package edu.ntnu.idatt1002.g106.handballapp.mvp.backend;

import java.util.List;

public class Administrator extends User{

    private TournamentRegister tournamentregister;

    public Administrator(User user, TournamentRegister tournamentRegister){
        super(user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail());
        this.tournamentregister = tournamentRegister;
    }


    public void editScoreResults(int tournamentID,int matchID, String teamName, int score){
        for(Tournament i: this.tournamentregister.getTournaments()){
            if(i.getTournamentID() == tournamentID){
                for(Match j: i.getTournament()){
                    if(j.getMatchID() == matchID){
                        j.setScore(teamName,score);
                    }
                }
            }
        }
    }

    public boolean addToResults(int tournamentID, int matchID){
        for(Tournament tournament : this.tournamentregister.getTournaments()){
            if(tournament.getTournamentID() == tournamentID){
                for(Match match : tournament.getTournament()){
                    if(match.getMatchID() == matchID) {
                        tournament.getResults().addToMatchResults(matchID, match);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void editTeamTotGoals(int tournamentID, String teamName, int newTotGoals){
        for(Tournament tournament: this.tournamentregister.getTournaments()){
            if(tournament.getTournamentID() == tournamentID){
                for(Team team: tournament.getTeamRegister().getTeams().values()){
                    if(team.getTeamName().equals(teamName)){
                        team.setTotGoals(newTotGoals);
                    }
                }
            }
        }
    }

    public void editTeamTotWins(int tournamentID, String teamName, int newTotWins){
        for(Tournament tournament: this.tournamentregister.getTournaments()){
            if(tournament.getTournamentID() == tournamentID){
                for(Team team: tournament.getTeamRegister().getTeams().values()){
                    if(team.getTeamName().equals(teamName)){
                        team.setTotGoals(newTotWins);
                    }
                }
            }
        }
    }

    public void editTeamTotLosses(int tournamentID, String teamName, int newTotLosses){
        for(Tournament tournament: this.tournamentregister.getTournaments()){
            if(tournament.getTournamentID() == tournamentID){
                for(Team team: tournament.getTeamRegister().getTeams().values()){
                    if(team.getTeamName().equals(teamName)){
                        team.setTotGoals(newTotLosses);
                    }
                }
            }
        }
    }


}
