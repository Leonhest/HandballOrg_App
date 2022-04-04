package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Nested
    public class Test_all_functions_for_constructor_and_thrown_exprssions{

        @Test
        public void Test_if_constructor_works_as_expected(){
            LocalDateTime startTimeTest = LocalDateTime.now();
            Team team1 = new Team("Name", "TeamLeader", "Region", 12, 94199891);
            Team team2 = new Team("Name2", "TeamLeader2", "Region2", 10, 47427756);
            Match matchTest = new Match(startTimeTest, 12, team1, team2, 321, 1);

            assertNotNull(matchTest);
        }

        @Test
        public void Testing_if_constructor_throws_IllegalArgumentException(){
            LocalDateTime startTimeTest = LocalDateTime.now();
            Team team1 = new Team("Name", "TeamLeader", "Region", 12, 94199891);
            Team team2 = new Team("Name2", "TeamLeader2", "Region2", 10, 47427756);


            assertThrows(IllegalArgumentException.class, () -> {
                Match matchTest = new Match(startTimeTest, 12, team1, team2, -1, 1);
            });
        }
    }

    @Nested
    public class Testing_all_setScore_methods_and_thrown_expressions{

        @Test
        public void Test_if_setScore_method_works_and_set_new_score_value(){
            LocalDateTime startTimeTest = LocalDateTime.now();
            Team team1 = new Team("Name", "TeamLeader", "Region", 12, 94199891);
            Team team2 = new Team("Name2", "TeamLeader2", "Region2", 10, 47427756);
            Match matchTest = new Match(startTimeTest, 12, team1, team2, 321, 1);

            matchTest.setScore("Name", 10);
            assertEquals(10, matchTest.getScoreByTeamName("Name"));
        }

        @Test
        public void Test_if_setScore_method_throws_error_when_score_is_negative(){
            LocalDateTime startTimeTest = LocalDateTime.now();
            Team team1 = new Team("Name", "TeamLeader", "Region", 12, 94199891);
            Team team2 = new Team("Name2", "TeamLeader2", "Region2", 10, 47427756);
            Match matchTest = new Match(startTimeTest, 12, team1, team2, 321, 1);

            assertThrows(IllegalArgumentException.class, () -> matchTest.setScore("Name", -1));
        }

        @Test
        public void Test_if_setScore_method_throws_error_when_team_name_isEmpty(){
            LocalDateTime startTimeTest = LocalDateTime.now();
            Team team1 = new Team("Name", "TeamLeader", "Region", 12, 94199891);
            Team team2 = new Team("Name2", "TeamLeader2", "Region2", 10, 47427756);
            Match matchTest = new Match(startTimeTest, 12, team1, team2, 321, 1);

            assertThrows(IllegalArgumentException.class, () -> matchTest.setScore("", 10));
        }
    }

    @Nested
    public class Test_all_get_Methods{

        @Test
        public void Test_if_getStartTime_works_as_expected(){
            LocalDateTime startTimeTest = LocalDateTime.now();
            LocalDateTime timeNow = LocalDateTime.now();
            Team team1 = new Team("Name", "TeamLeader", "Region", 12, 94199891);
            Team team2 = new Team("Name2", "TeamLeader2", "Region2", 10, 47427756);
            Match matchTest = new Match(startTimeTest, 12, team1, team2, 321, 1);

            assertEquals(timeNow.getMinute(), matchTest.getStartTime().getMinute());
        }
         @Test
         public void Test_if_getField_works_as_expected(){
            LocalDateTime startTimeTest = LocalDateTime.now();
            Team team1 = new Team("Name", "TeamLeader", "Region", 12, 94199891);
            Team team2 = new Team("Name2", "TeamLeader2", "Region2", 10, 47427756);
            Match matchTest = new Match(startTimeTest, 12, team1, team2, 321, 1);
                assertEquals(1, matchTest.getNumField());
        }

        @Test
        public void Test_if_getMatchID_works_as_expected(){
            LocalDateTime startTimeTest = LocalDateTime.now();
            Team team1 = new Team("Name", "TeamLeader", "Region", 12, 94199891);
            Team team2 = new Team("Name2", "TeamLeader2", "Region2", 10, 47427756);
            Match matchTest = new Match(startTimeTest, 12, team1, team2, 321, 1);

            assertEquals(321, matchTest.getMatchID());
        }

        @Test
        public void Test_if_getRoundNum_works_as_expected(){
            LocalDateTime startTimeTest = LocalDateTime.now();
            Team team1 = new Team("Name", "TeamLeader", "Region", 12, 94199891);
            Team team2 = new Team("Name2", "TeamLeader2", "Region2", 10, 47427756);
            Match matchTest = new Match(startTimeTest, 12, team1, team2, 321, 1);

            assertEquals(12, matchTest.getRoundNum());
        }

        @Test
        public void Test_if_getFinalResult_works_as_expected(){
            LocalDateTime startTimeTest = LocalDateTime.now();
            Team team1 = new Team("Name", "TeamLeader", "Region", 12, 94199891);
            Team team2 = new Team("Name2", "TeamLeader2", "Region2", 10, 47427756);
            Match matchTest = new Match(startTimeTest, 12, team1, team2, 321, 1);

            assertEquals("0 - 0", matchTest.getFinalResult());
        }
    }
}