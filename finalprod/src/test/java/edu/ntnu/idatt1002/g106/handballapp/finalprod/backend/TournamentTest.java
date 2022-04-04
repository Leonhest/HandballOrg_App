package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TournamentTest {

    @Test
    public void possible_to_set_up_valid_tournament() {
        int tournamentId = 1;
        String tournamentName = "Oslo opne";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(2022,12, 1);
        Tournament tournament = null;
        String layout = "layout1";
        String tournamentPlace = "Oslo";
        int numFields = 3;
        int numTeams = 8;

        try {
            tournament = new Tournament(tournamentId, tournamentName,startDate, endDate, layout, tournamentPlace, numFields, numTeams);
        } catch (Exception e) {
            fail("Did not initiate");
        }
        assertEquals(tournamentId, tournament.getTournamentID());
        assertEquals(startDate, tournament.getStartDate());
        assertEquals(endDate, tournament.getEndDate());
    }


    @Nested
    public class addMatch{
        @Test
        public void possible_to_add_a_new_valid_match() {
            Tournament tournament = new Tournament(1, "Oslo open",LocalDate.now(), LocalDate.of(2022,12, 1), "layout1", "Oslo", 3, 8);
            Team team1 = new Team("Sandefjord", "Ola Nordmann", "Oslo", 16, 98765432);
            Team team2 = new Team("Oslo", "Eirik Nordmann", "Oslo", 16, 98765431);
            Match match = new Match(LocalDateTime.now(), 1, team1, team2, 1, 1);

            assertTrue(tournament.addMatch(match));
        }

        @Test
        public void impossible_to_add_a_existing_match() {
            Tournament tournament = new Tournament(1, "Oslo open", LocalDate.now(), LocalDate.of(2022,12, 1), "layout1", "Oslo", 3, 8);
            Team team1 = new Team("Sandefjord", "Ola Nordmann", "Oslo", 16, 98765432);
            Team team2 = new Team("Oslo", "Eirik Nordmann", "Oslo", 16, 98765431);
            Match match = new Match(LocalDateTime.now(), 1, team1, team2, 1, 1);

            tournament.addMatch(match);
            assertFalse(tournament.addMatch(match));
        }

        @ParameterizedTest
        @NullSource
        public void impossible_to_add_a_null_match(Match match) {
            Tournament tournament = new Tournament(1, "Oslo open", LocalDate.now(), LocalDate.of(2022,12, 1),"layout1", "Oslo", 3, 8);

            assertFalse(tournament.addMatch(match));
        }
    }

    @Nested
    public class removeMatchByMatchID{
        @Test
        public void possible_to_remove_match_with_valid_matchId() {
            Tournament tournament = new Tournament(1, "Oslo open", LocalDate.now(), LocalDate.of(2022,12, 1),"layout1", "Oslo", 3, 8);
            Team team1 = new Team("Sandefjord", "Ola Nordmann", "Oslo", 16, 98765432);
            Team team2 = new Team("Oslo", "Eirik Nordmann", "Oslo", 16, 98765431);
            Match match = new Match(LocalDateTime.now(), 1, team1, team2, 1, 1);
            
            tournament.addMatch(match);

            assertTrue(tournament.removeMatchByMatchID(1));
        }

        @Test
        public void impossible_to_remove_match_with_invalid_matchId() {
            Tournament tournament = new Tournament(1, "Oslo open", LocalDate.now(), LocalDate.of(2022,12, 1), "layout1", "Oslo", 3, 8);
            assertFalse(tournament.removeMatchByMatchID(-1));
        }
    }
}