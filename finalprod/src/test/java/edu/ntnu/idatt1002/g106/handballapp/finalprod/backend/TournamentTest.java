package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class TournamentTest {

    static Tournament createTournament(){
        int tournamentId = 1;
        String tournamentName = "Oslo open";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(1);
        String tournamentPlace = "Oslo";
        String region = "South";
        int numFields = 3;
        int numTeams = 32;

        return new Tournament(tournamentId, tournamentName,startDate, endDate, tournamentPlace, numFields, numTeams, region);
    }

    @Test
    public void possible_to_set_up_valid_tournament() {
        int tournamentId = 1;
        String tournamentName = "Oslo opne";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(2022,12, 1);
        Tournament tournament = null;
        String tournamentPlace = "Oslo";
        int numFields = 3;
        int numTeams = 8;
        String region = "RegionSor";

        try {
            tournament = new Tournament(tournamentId, tournamentName,startDate, endDate, tournamentPlace, numFields, numTeams, region);
        } catch (Exception e) {
            fail("Did not initiate");
        }
        assertEquals(tournamentId, tournament.getTournamentID());
        assertEquals(startDate, tournament.getStartDate());
        assertEquals(endDate, tournament.getEndDate());
    }

    @Test
    void time_per_interval_for_two_days(){
        Tournament tournament = createTournament();
        int totalTime = 2 * 12;
        int timePerInterval = totalTime / tournament.totalIntervalsNeeded();
        System.out.println(timePerInterval);
    }

    @Test
    void generateTournament() {
        Tournament tournament = createTournament();
        tournament.generateTournament();
        System.out.println("\n\n");
        for(int i = 0; i < tournament.checkAmountRounds(); i++){
            System.out.println("Round" + (i+1) + ":");
            for(Match match : tournament.getRoundMatchList().get(i)){
                System.out.println(match.getStartTime());
            }
        }



    }

    @Test
    void matching_schedule(){
        Tournament tournament = createTournament();
//        System.out.println(tournament.totalIntervalsNeeded());
        System.out.println(tournament.makeSchedule());
        System.out.println(tournament.totalIntervalsNeeded());
        for(int i = 0; i < tournament.makeSchedule().size(); i++){
            System.out.println((i+1) + ": " + tournament.makeSchedule().get(i));
        }
    }

    @Test
    void check_Intervals(){
        Tournament tournament = createTournament();
        System.out.println(tournament.totalIntervalsNeeded());
        System.out.println(tournament.checkNumMatchesByRound(1));
        System.out.println(tournament.intervalTakenByRound(1));
        System.out.println(tournament.makeSchedule().size());
    }

    @Test
    void tempTime_returns_a_filled_list(){
        Tournament tournament = createTournament();
        Assertions.assertTrue(tournament.makeSchedule().size() > 0);
    }


    @Nested
    public class addMatch{
        @Test
        public void possible_to_add_a_new_valid_match() {
            Tournament tournament = new Tournament(1, "Oslo open",LocalDate.now(), LocalDate.of(2022,12, 1), "Oslo", 3, 8, "RegionSor");
            Team team1 = new Team("Sandefjord", "Ola Nordmann", "Oslo", 16, 98765432);
            Team team2 = new Team("Oslo", "Eirik Nordmann", "Oslo", 16, 98765431);
            Match match = new Match(LocalDateTime.now(), 1, team1, team2, 1, 1);

            assertTrue(tournament.addMatch(match));
        }

        @Test
        public void impossible_to_add_a_existing_match() {
            Tournament tournament = new Tournament(1, "Oslo open", LocalDate.now(), LocalDate.of(2022,12, 1), "Oslo", 3, 8, "RegionSor");
            Team team1 = new Team("Sandefjord", "Ola Nordmann", "Oslo", 16, 98765432);
            Team team2 = new Team("Oslo", "Eirik Nordmann", "Oslo", 16, 98765431);
            Match match = new Match(LocalDateTime.now(), 1, team1, team2, 1, 1);

            tournament.addMatch(match);
            assertFalse(tournament.addMatch(match));
        }

        @ParameterizedTest
        @NullSource
        public void impossible_to_add_a_null_match(Match match) {
            Tournament tournament = new Tournament(1, "Oslo open", LocalDate.now(), LocalDate.of(2022,12, 1), "Oslo", 3, 8, "RegionSor");

            assertFalse(tournament.addMatch(match));
        }
    }

    @Nested
    public class removeMatchByMatchID{
        @Test
        public void possible_to_remove_match_with_valid_matchId() {
            Tournament tournament = new Tournament(1, "Oslo open", LocalDate.now(), LocalDate.of(2022,12, 1), "Oslo", 3, 8, "RegionSor");
            Team team1 = new Team("Sandefjord", "Ola Nordmann", "Oslo", 16, 98765432);
            Team team2 = new Team("Oslo", "Eirik Nordmann", "Oslo", 16, 98765431);
            Match match = new Match(LocalDateTime.now(), 1, team1, team2, 1, 1);
            
            tournament.addMatch(match);

            assertTrue(tournament.removeMatchByMatchID(1));
        }

        @Test
        public void impossible_to_remove_match_with_invalid_matchId() {
            Tournament tournament = new Tournament(1, "Oslo open", LocalDate.now(), LocalDate.of(2022,12, 1), "Oslo", 3, 8, "RegionSor");
            assertFalse(tournament.removeMatchByMatchID(-1));
        }
    }
}