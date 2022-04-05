package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;
/**
 * This is a class that creates an administrator
 */
public class Administrator extends User{

    /**
     * This constructor allows for an Administrator object to be initialized with the necessary information to define
     * an admin and their privileges.
     * @param user                  User to be added, with info included
     */
    public Administrator(User user){
        super(user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail());
    }
}
