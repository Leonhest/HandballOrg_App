package edu.ntnu.idatt1002.g106.handballapp.mvp.backend;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TournamentTest {

    @Test
    public void possible_to_set_up_valid_tournament() {
        int tournamentId = 1;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(2022,12, 1);
        Tournament tournament = null;

        try {
            tournament = new Tournament(tournamentId, startDate, endDate);
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
            Tournament tournament = new Tournament(1, LocalDate.now(), LocalDate.of(2022,12, 1));
            Team team1 = new Team("Sandefjord", "Ola Nordmann", "Oslo", 16, 98765432);
            Team team2 = new Team("Oslo", "Eirik Nordmann", "Oslo", 16, 98765431);
            Match match = new Match(LocalTime.now(), 1, team1, team2, 1, 1);

            assertTrue(tournament.addMatch(match));
        }

        @Test
        public void impossible_to_add_a_consisting_match() {
            Tournament tournament = new Tournament(1, LocalDate.now(), LocalDate.of(2022,12, 1));
            Team team1 = new Team("Sandefjord", "Ola Nordmann", "Oslo", 16, 98765432);
            Team team2 = new Team("Oslo", "Eirik Nordmann", "Oslo", 16, 98765431);
            Match match = new Match(LocalTime.now(), 1, team1, team2, 1, 1);

            tournament.addMatch(match);
            assertFalse(tournament.addMatch(match));
        }

        @ParameterizedTest
        @NullSource
        public void impossible_to_add_a_consisting_match(Match match) {
            Tournament tournament = new Tournament(1, LocalDate.now(), LocalDate.of(2022,12, 1));

            assertFalse(tournament.addMatch(match));
        }
    }

    @Nested
    public class removeMatchByMatchID{
        @Test
        public void possible_to_remove_match_with_valid_matchId() {
            Tournament tournament = new Tournament(1, LocalDate.now(), LocalDate.of(2022,12, 1));
            Team team1 = new Team("Sandefjord", "Ola Nordmann", "Oslo", 16, 98765432);
            Team team2 = new Team("Oslo", "Eirik Nordmann", "Oslo", 16, 98765431);
            Match match = new Match(LocalTime.now(), 1, team1, team2, 1, 1);
            
            tournament.addMatch(match);

            assertTrue(tournament.removeMatchByMatchID(1));
        }

        @Test
        public void impossible_to_remove_match_with_invalid_matchId() {
            Tournament tournament = new Tournament(1, LocalDate.now(), LocalDate.of(2022,12, 1));
            assertFalse(tournament.removeMatchByMatchID(-1));
        }
    }
}