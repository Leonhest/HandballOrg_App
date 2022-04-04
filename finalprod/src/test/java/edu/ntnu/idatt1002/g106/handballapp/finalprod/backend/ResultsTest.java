package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ResultsTest {
    @Nested
    public class addMatchToResults {
        @Test
        public void possible_to_add_match_results_to_results() {
            Results results = new Results();
            Team team1 = new Team("Sandefjord", "Ola Nordmann", "Oslo", 16, 98765432);
            Team team2 = new Team("Oslo", "Eirik Nordmann", "Oslo", 16, 98765431);
            Match match = new Match(LocalDateTime.now(), 1, team1, team2, 1, 1);

            assertTrue(results.addMatchToResults(match));
            assertEquals(1, results.getMatchResults().size());
            assertEquals(match, results.getMatchResults().get(match.getMatchID()));
        }

        //TODO: What is this? Parameterized without parameters?
        @ParameterizedTest
        @NullSource
        public void impossible_to_add_null_match_to_results(Match match) {
            Results results = new Results();
            assertFalse(results.addMatchToResults(match));
        }

        @Test
        public void impossible_to_add_existing_match_results_to_results() {
            Results results = new Results();
            Team team1 = new Team("Sandefjord", "Ola Nordmann", "Oslo", 16, 98765432);
            Team team2 = new Team("Oslo", "Eirik Nordmann", "Oslo", 16, 98765431);
            Match match = new Match(LocalDateTime.now(), 1, team1, team2, 1, 1);

            results.addMatchToResults(match);

            assertFalse(results.addMatchToResults(match));
            assertEquals(1, results.getMatchResults().size());
        }
    }

}