package edu.ntnu.idatt1002.g106.handballapp.finalprod.backend;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Represents a user of the application
 */
public class User {
    private final Pattern emailPattern = Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[A-Z]{2,5}$", Pattern.CASE_INSENSITIVE);
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private byte[] salt;

    /**
     * Initializes a User object
     *
     * @param firstName     First name of user as String
     * @param lastName      Last name of user as String
     * @param password      User password as String
     * @param email         User Email as String
     */
    public User(String firstName, String lastName, String password, String email) throws NullPointerException{
        if(firstName.isEmpty() || firstName.isBlank() || lastName.isEmpty() || lastName.isBlank()) throw new
                IllegalArgumentException("First and/or last name is invalid due to being empty or blank");
        this.firstName = firstName;
        this.lastName = lastName;
        Matcher matcher = emailPattern.matcher(email);
        if(!matcher.matches()) throw new IllegalArgumentException("Email is invalid, should be in the form ---@---.---");
        this.email = email;
        this.salt = generateSalt();
        this.password = hashPassword(password, this.salt);
    }

    /**
     * Gets first name os user
     *
     * @return  first name of user as String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name of user
     *
     * @param firstName new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets last name of user
     *
     * @return  last name of user as String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name of user
     *
     * @param lastName new last name as String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets hashed user password
     *
     * @return  hashed user password as String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Hashes and sets new user password
     *
     * @param password  new password as String
     */
    public void setPassword(String password) {
        this.password = hashPassword(password, this.salt);
    }

    /**
     * Gets user Email
     *
     * @return user Email as String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets new user Email
     *
     * @param email new Email as String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets salt for hashing passwords
     *
     * @return salt as byte array
     */
    public byte[] getSalt(){
        return this.salt;
    }

    /**
     * This method changes a User's password to a new given password.
     * @param oldPassword The User's old password, represented as a String
     * @param newPassword The User's new password, represented as a String
     * @return            Status of whether the password was successfully changed, true if it was, false if else
     */
    public boolean changePassword(String oldPassword, String newPassword){
        if(hashPassword(oldPassword, this.salt).equals(this.password)){
            setPassword(newPassword);
            return true;
        }
        else return false;

    }

    /**
     * Generates a salt, for hashing
     * @return a random salt
     */
    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * Method to hash a password with salt
     * @param password password to be hashed
     * @param salt salt to use when hashing
     * @return hashedPassword, null if unsuccessful
     */
    public String hashPassword(String password, byte[] salt){
        String sha256Pass = null;
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : salt) {
            stringBuilder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        String saltstring = stringBuilder.toString();
        sha256Pass = DigestUtils.sha256Hex(password + saltstring);
        return sha256Pass;

    }
}