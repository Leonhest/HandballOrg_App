package edu.ntnu.idatt1002.g106.handballapp.mvp.backend;


import java.util.Objects;

/**
 * This is a class that creates an administrator
 */
public class Administrator extends User{

    private TournamentRegister tournamentRegister;

    /**
     * This constructor allows for an Administrator object to be initialized with the necessary information to define
     * an admin and their privileges.
     * @param user                  User to be added, with info included
     */
    public Administrator(User user){
        super(user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail());
        this.tournamentRegister = new TournamentRegister();
    }

    /**
     * This method retrieves the tournament register.
     * @return The tournament register
     */
    public TournamentRegister getTournamentRegister() {
        return tournamentRegister;
    }
}
