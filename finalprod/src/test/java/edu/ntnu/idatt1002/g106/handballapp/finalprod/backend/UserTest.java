package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Nested
    class Tests_of_constructor {
        @Test
        public void if_constructor_works_as_expected() {
            User userTest = null;
            try {
                userTest = new User("Tomas", "Beranek", "1234", "tomas@gmail.com");
            } catch (Exception e) {
                fail("Preset constructor did not instantiate properly");
            }

            assertEquals("Tomas", userTest.getFirstName());
            assertEquals("Beranek", userTest.getLastName());
            assertEquals("tomas@gmail.com", userTest.getEmail());

            assertNotNull(userTest);
        }

        @Test
        public void if_constructor_throws_NullPointerException() {
            assertThrows(NullPointerException.class, () -> {
                User userTest = new User("Tomas", null, "1234", "tomas@gmail.com");
            });
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "           "}) //Arrange
        void if_first_name_input_is_empty_or_blank(String firstName){
            assertThrows(IllegalArgumentException.class, ()->{
                User userTest = new User(firstName, "Beranek", "1234", "tomas@gmail.com");
                //Act and assert
            });
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "           "}) //Arrange
        void if_last_name_input_is_empty_or_blank(String lastName){
            assertThrows(IllegalArgumentException.class, ()->{
                User userTest = new User("Tomas", lastName, "1234", "tomas@gmail.com");
                //Act and assert
            });
        }

    }

    @Nested
    class Tests_for_email {
        @ParameterizedTest
        @ValueSource(strings = {"Leonhe@stud.ntnu.no", "trym.gud@gmail.com"})
            //Arrange
        void if_a_valid_email_does_not_throw_exception(String validEmail) {
            User invalidUser = null;
            try {
                invalidUser = new User("Trym", "Gudvangen", "123", validEmail);
            } catch (Exception e) {
                fail("Preset constructor did not instantiate properly");
            }
            assertTrue(true);

        }

        @ParameterizedTest
        @ValueSource(strings = {"", "@stud.no ", "@.", "  ", "trymhg@stud.noooooo"})
            //Arrange
        void if_an_invalid_email_throws_IllegalArgumentException(String invalidEmail) {
            assertThrows(IllegalArgumentException.class, () -> {
                User invalidUser = new User("Trym", "Gudvangen", "123", invalidEmail);
                //Act and assert
            });
        }
    }
}