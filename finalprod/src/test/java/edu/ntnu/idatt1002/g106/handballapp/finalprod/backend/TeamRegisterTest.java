package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamRegisterTest {

    @Nested
    class AddTeamTest {
        @Test
        @DisplayName("addTeam adds team to register")
        void addTeam_Adds_Team_to_Register() {
            //Arrange
            Team team1 = new Team("Gutta krutt", "Tore Tang", "Asker", 12, 95068796);
            TeamRegister teamRegister = new TeamRegister();
            //Act
            teamRegister.addTeam(team1);
            //Assert
            assertEquals(team1, teamRegister.getTeams().get(team1.getTeamName()));
        }

        @Test
        @DisplayName("addTeam returns false for null")
        void addTeam_Returns_False_for_Null() {
            //Arrange
            Team team1 = null;
            TeamRegister teamRegister = new TeamRegister();
            //Act
            boolean addbool = teamRegister.addTeam(team1);
            //Assert
            assertFalse(addbool);
        }

        @Test
        @DisplayName("addTeam returns false if team already exists")
        void addTeam_Returns_False_If_Team_Already_Exists() {
            //Arrange
            Team team1 = new Team("Gutta krutt", "Tore Tang", "Asker", 12, 95068796);
            TeamRegister teamRegister = new TeamRegister();
            //Act
            teamRegister.addTeam(team1);
            boolean addbool = teamRegister.addTeam(team1);
            //Assert
            assertFalse(addbool);
        }
    }

    @Nested
    class RemoveTeamTest{

        @Test
        @DisplayName("removeTeam removes team")
        void removeTeam_Removes_Team() {
            //Arrange
            Team team1 = new Team("Gutta krutt", "Tore Tang", "Asker", 12, 95068796);
            TeamRegister teamRegister = new TeamRegister();
            //Act
            teamRegister.addTeam(team1);
            teamRegister.removeTeam(team1);
            //Assert
            assertTrue(teamRegister.getTeams().isEmpty());
        }

        @Test
        @DisplayName("removeTeam returns false if team does not exist")
        void removeTeam_Returns_False_If_Team_Does_Not_Exist() {
            //Arrange
            Team team1 = new Team("Gutta krutt", "Tore Tang", "Asker", 12, 95068796);
            TeamRegister teamRegister = new TeamRegister();
            //Act
            boolean rembool = teamRegister.removeTeam(team1);
            //Assert
            assertFalse(rembool);
        }
    }

    @Nested
    class findTeamBasedOnTeamName{
        @Test
        void possible_to_find_team_based_on_teamName() {
            String teamName = "Gutta krutt";
            TeamRegister teamRegister = new TeamRegister();
            Team expectedTeam = new Team(teamName, "Tore Tang", "Asker", 12, 95068796);

            teamRegister.addTeam(expectedTeam);

            assertEquals(expectedTeam, teamRegister.findTeamBasedOnTeamName(teamName));
        }

        @Test
        void returns_null_if_the_teamRegister_do_not_contains_the_given_teamName() {
            TeamRegister teamRegister = new TeamRegister();
            String teamName = "Gutta krutt";

            assertNull(teamRegister.findTeamBasedOnTeamName(teamName));
        }
    }

    @Nested
    class EditTotWinsTest{

        @Test
        @DisplayName("editTotWins edits wins")
        void editTotWins_Edits_Wins(){
            //Arrange
            Team team1 = new Team("Gutta krutt", "Tore Tang", "Asker", 12, 95068796);
            TeamRegister teamRegister = new TeamRegister();
            int totWins = 3;
            //Act
            teamRegister.addTeam(team1);
            teamRegister.editTotWins(team1.getTeamName(), totWins);
            //Assert
            assertEquals(totWins, team1.getTotWins());
        }

        @Test
        @DisplayName("editTotWins throws IllegalArgumentException if negative wins")
        void editTotWins_Throws_IllegalArgument_Exception_If_Negative_Wins(){
            //Arrange
            Team team1 = new Team("Gutta krutt", "Tore Tang", "Asker", 12, 95068796);
            TeamRegister teamRegister = new TeamRegister();
            int totWins = -1;
            //Act
            teamRegister.addTeam(team1);

            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                teamRegister.editTotWins(team1.getTeamName(), totWins);
            });
        }
    }

    @Nested
    class editTotLosses{

        @Test
        @DisplayName("editTotLosses edits losses")
        void editTotLosses_Edits_Losses(){
            //Arrange
            Team team1 = new Team("Gutta krutt", "Tore Tang", "Asker", 12, 95068796);
            TeamRegister teamRegister = new TeamRegister();
            int totLosses = 3;
            //Act
            teamRegister.addTeam(team1);
            teamRegister.editTotLosses(team1.getTeamName(), totLosses);
            //Assert
            assertEquals(totLosses, team1.getTotLosses());
        }

        @Test
        @DisplayName("editTotLosses throws IllegalArgumentException if negative losses")
        void editTotLosses_Throws_IllegalArgument_Exception_If_Negative_Losses(){
            //Arrange
            Team team1 = new Team("Gutta krutt", "Tore Tang", "Asker", 12, 95068796);
            TeamRegister teamRegister = new TeamRegister();
            int totLosses = -1;
            //Act
            teamRegister.addTeam(team1);

            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                teamRegister.editTotLosses(team1.getTeamName(), totLosses);
            });
        }
    }

    @Nested
    class EditTotGoalsTest{

        @Test
        @DisplayName("editTotGoals edits wins")
        void editTotGoals_Edits_Goals(){
            //Arrange
            Team team1 = new Team("Gutta krutt", "Tore Tang", "Asker", 12, 95068796);
            TeamRegister teamRegister = new TeamRegister();
            int totGoals = 3;
            //Act
            teamRegister.addTeam(team1);
            teamRegister.editTotGoals(team1.getTeamName(), totGoals);
            //Assert
            assertEquals(totGoals, team1.getTotGoals());
        }

        @Test
        @DisplayName("editTotGoals throws IllegalArgumentException if negative goals")
        void editTotGoals_Throws_IllegalArgument_Exception_If_Negative_Goals(){
            //Arrange
            Team team1 = new Team("Gutta krutt", "Tore Tang", "Asker", 12, 95068796);
            TeamRegister teamRegister = new TeamRegister();
            int totGoals = -1;
            //Act
            teamRegister.addTeam(team1);

            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                teamRegister.editTotWins(team1.getTeamName(), totGoals);
            });
        }
    }

    @Nested
    class EditNumPlayers{

        @Test
        @DisplayName("editNumPlayers edits number of players")
        void editNumPlayers_Edits_num_of_players(){
            //Arrange
            Team team1 = new Team("Gutta krutt", "Tore Tang", "Asker", 12, 95068796);
            TeamRegister teamRegister = new TeamRegister();
            int numPlayers = 10;
            //Act
            teamRegister.addTeam(team1);
            teamRegister.editNumPlayers(team1.getTeamName(), numPlayers);
            //Assert
            assertEquals(numPlayers, team1.getNumPlayers());
        }

        @Test
        @DisplayName("editNumPlayers throws exception if number of players i less than 0")
        void editNumPlayers_Throws_IllegalArgumentExeption_if_num_of_players_is_less_then_zero(){
            //Arrange
            Team team1 = new Team("Gutta krutt", "Tore Tang", "Asker", 12, 95068796);
            TeamRegister teamRegister = new TeamRegister();
            int numPlayers = -1;
            //Act
            teamRegister.addTeam(team1);
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                teamRegister.editNumPlayers(team1.getTeamName(), numPlayers);
            });
        }

    }

}