package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TournamentRegisterTest {

    @Test
    void addTournament_returns_false_when_tournamentID_is_the_similar(){
        //Arrange
        TournamentRegister tournamentRegister = new TournamentRegister();
        Tournament tournament1 = new Tournament(1, "Oslo open",LocalDate.now(), LocalDate.now(),"layout1", "Oslo", 3, 8, "RegionSor");
        Tournament tournament2 = new Tournament(1, "Oslo open",LocalDate.now(), LocalDate.now(), "layout1", "Oslo", 3, 8, "RegionOst");
        boolean expectedStatus = false;

        //Act
        tournamentRegister.addTournament(tournament1);
        boolean actualStatusOfAdding = tournamentRegister.addTournament(tournament2);

        //Assert
        assertEquals(expectedStatus, actualStatusOfAdding);
    }

}