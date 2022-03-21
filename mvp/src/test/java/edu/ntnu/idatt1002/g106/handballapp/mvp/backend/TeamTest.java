package edu.ntnu.idatt1002.g106.handballapp.mvp.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void Test_if_constructor_is_properly_initialized(){
        //Given/Arrange
        String teamName = "Flame ball";
        String teamLeader = "Leon";
        String region = "Sandefjord";
        int numPlayers = 15;
        int telephoneNum = 342345235;

        //When/Act
        Team team = null;
        try {
            team = new Team(teamName, teamLeader, region, numPlayers, telephoneNum);
        } catch (Exception e) {
            fail("Preset constructor did not instantiate properly");
        }

        //Then/Assert
        Assertions.assertEquals(teamName, team.getTeamName());
        Assertions.assertEquals(teamLeader, team.getTeamLeader());
        Assertions.assertEquals(region, team.getRegion());
        Assertions.assertEquals(numPlayers, team.getNumPlayers());
        Assertions.assertEquals(telephoneNum, team.getTelephoneNum());

    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 6}) //Arrange
    void Test_if_number_is_invalid_throws_IllegalArgumentException(int numPlayers){
        assertThrows(IllegalArgumentException.class, ()->{
            Team team = new Team("Flame Ball", "Leon", "Sandefjord", numPlayers,  34589000);
            //Act and assert
        });
    }

}