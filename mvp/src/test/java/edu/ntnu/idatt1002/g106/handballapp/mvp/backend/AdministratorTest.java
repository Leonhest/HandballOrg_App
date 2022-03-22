package edu.ntnu.idatt1002.g106.handballapp.mvp.backend;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class AdministratorTest {

    @Test
    public void Test_if_constructor_works_as_expected(){
        User userTest = new User("Tomas", "Beranek", "1234", "tomas@gmail.com");

        Administrator administratorTest = new Administrator(userTest);
        assertNotNull(administratorTest);
    }

    @Test
    public void Test_if_constructor_throws_null_pointer_exception_when_User_is_null(){
        //Arrange
        User userTest = null;

        assertThrows(NullPointerException.class, ()->{
            Administrator administratorTest = new Administrator(userTest);
            //Act and assert
        });
    }

}