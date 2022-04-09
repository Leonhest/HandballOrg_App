package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Map;

/**
 * this class is a construction class that describes how a field is going to look like;
 * and different methods that will be used to handle a field
 * @author Gruppe 6
 */
public class Field {
    private int numField;
    private Map<LocalDateTime, Boolean> availabilitySchedule;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * this is the class constructor with needed variables
     * @param numField
     * @param startDate
     * @param endDate
     */
    public Field(int numField, LocalDate startDate, LocalDate endDate) {
        this.numField = numField;
        generateTimeSchedule(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;

        //Todo: exception handling
    }

    @Deprecated
    /**
     * This method sets the availability for a given time as false.
     * @param localDateTime
     */
    public void setAvailabilityBasedOnTime(LocalDateTime localDateTime){
        this.availabilitySchedule.replace(localDateTime, false);
    }


    @Deprecated
    /**
     * This method checks whether the date given falls within the available times of the tournament
     * @param localDateTime Given date and time
     * @return              The status on whether it is within the interval or not
     */
    public boolean isAvailableInGivenTimeInterval(LocalDateTime localDateTime){
        if(((localDateTime.getHour() + (localDateTime.getMinute()/60)) % 1.5) != 0 && isWithinTournamentDays(localDateTime)){
            return availabilitySchedule.get(localDateTime);
        }
        return false; //This method is unfinished and not in use.

    }

    @Deprecated
    /**
     * This method is to be used in checking that a given date falls within the tournament.
     * @param localDateTime A given date
     * @return              Status of whether the date falls within the tournament dates
     */
    public boolean isWithinTournamentDays(LocalDateTime localDateTime){
        return localDateTime.isAfter(ChronoLocalDateTime.from(startDate)) ||
                localDateTime.isEqual(ChronoLocalDateTime.from(startDate)) ||
                localDateTime.isBefore(ChronoLocalDateTime.from(endDate)) ||
                localDateTime.isEqual(ChronoLocalDateTime.from(endDate));
    }

    @Deprecated
    /**
     * This method generates a time schedule for when tournaments are set up manually.
     * @param startDate
     * @param endDate
     */
    private void generateTimeSchedule(LocalDate startDate, LocalDate endDate){
        int hours = 0;
        int minutes = 0;
        LocalDate dateOfTournament = startDate;
        while(dateOfTournament.isBefore(endDate.plusDays(1))){
            for(int i = 1; i < 10; i++){
                if((i*1.5) % 1 == 0){
                    hours = (int) (9 + (i*1.5));
                    minutes = 0;
                }
                else{
                    hours = (int) (9 + Math.floor(i * 1.5));
                    minutes = 30;
                }
                availabilitySchedule.put(LocalDateTime.of(dateOfTournament, LocalTime.of(hours, minutes)), true);
            }
            dateOfTournament = dateOfTournament.plusDays(1);
        }
    }

    /**
     * toString method that prints out the fields information
     * @return
     */
    @Override
    public String toString() {
        return "Field{" +
                "numField=" + numField +
                ", availabilitySchedule=" + availabilitySchedule +
                '}';
    }
}
