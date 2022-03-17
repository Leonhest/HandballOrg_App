package edu.ntnu.idatt1002.g106.handballapp.mvp.backend;


/**
 * This is a class that creates an administrator
 */
public class Administrator extends User{

    private TournamentRegister tournamentregister;

    /**
     * This constructor allows for an Administrator object to be initialized with the necessary information to define
     * an admin and their privileges.
     * @param user
     * @param tournamentRegister
     */
    public Administrator(User user, TournamentRegister tournamentRegister){
        super(user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail());
        this.tournamentregister = tournamentRegister;
    }

}
