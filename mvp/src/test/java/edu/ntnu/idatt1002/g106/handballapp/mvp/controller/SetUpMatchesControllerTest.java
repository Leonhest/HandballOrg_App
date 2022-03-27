package edu.ntnu.idatt1002.g106.handballapp.mvp.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class SetUpMatchesControllerTest {
    Pattern hourMinPat = Pattern.compile("^[0-1]\\d:[0-5]\\d${5}");

    @ParameterizedTest
    @ValueSource(strings = {"00:00", "12:59", "09:39"}) //Arrange
    void if_regex_matches_when_hour_min_input_is_valid(String inputTime){
        //Arrange
        Matcher matcher = hourMinPat.matcher(inputTime);
        //Act
        boolean statusOnRegex = matcher.matches();
        //Assert
        Assertions.assertTrue(statusOnRegex);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "12:123", "25:12", "12:61", ""}) //Arrange
    void if_regex_sends_false_when_hour_min_input_is_invalid(String inputTime){
        //Arrange
        Matcher matcher = hourMinPat.matcher(inputTime);
        //Act
        boolean statusOnRegex = matcher.matches();
        //Assert
        Assertions.assertFalse(statusOnRegex);
    }

    @Test
    void if_format_method_works_properly(){
        //Arrange
        String validTime = "12:15";
        int expectedHours = 12;
        int expectedMinutes = 15;

        //Act
        List<Integer> listOfHoursAndMin = createHourMinList(validTime);
        int actualHours = listOfHoursAndMin.get(0);
        int actualMinutes = listOfHoursAndMin.get(1);

        //Assert
        Assertions.assertEquals(expectedHours, actualHours);
        Assertions.assertEquals(expectedMinutes, actualMinutes);
    }

    public List<Integer> createHourMinList(CharSequence stringToBeConverted){
        return Arrays.stream(stringToBeConverted.toString().split(":"))
                .map(timeUnit -> Integer.valueOf(timeUnit)).toList();
    }
}