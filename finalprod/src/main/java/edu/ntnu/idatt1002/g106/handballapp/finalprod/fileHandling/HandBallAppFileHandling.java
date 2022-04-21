package edu.ntnu.idatt1002.g106.handballapp.finalprod.fileHandling;

import edu.ntnu.idatt1002.g106.handballapp.finalprod.backend.TournamentRegister;

import java.io.*;

/**
 * This class provides method to serialize and deserialize a TournamentRegister
 *
 * @author Group 6
 */
public class HandBallAppFileHandling {

    /**
     * Method serializes an {@code param army} to the {@code param file}
     * @param tournamentRegister The tournamentRegister to serialize
     * @param file The file to serialize to
     * @return {@code true} if the serialization succeed or {@code false} if the {@code param tournamentRegister} is null
     * @throws IOException if the file exists but is a directory rather than
     *                     a regular file, does not exist but cannot be created,
     *                     or cannot be opened for any other reason
     */
    public static boolean serializeTournamentRegister(TournamentRegister tournamentRegister, File file) throws IOException {

        if (tournamentRegister == null) {
            return false;
        }
        try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(tournamentRegister);
            return true;
        }
    }

    /**
     * Deserialize a tournamentRegister from the {@code param file}
     * @param file The file to deserialize from
     * @return The deserialized tournamentRegister if there is registered a tournamentRegister, otherwise null
     * @throws IOException if the file exists but is a directory rather than
     *                     a regular file, does not exist but cannot be created,
     *                     or cannot be opened for any other reason
     * @throws ClassNotFoundException if the class of the serialized object cannot be found
     */
    public static TournamentRegister deserializeTournamentRegister(File file) throws IOException, ClassNotFoundException {
        if (file.length() == 0) {
            return null;
        }

        TournamentRegister tournamentRegister = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
            tournamentRegister = (TournamentRegister) ois.readObject();
        }
        return tournamentRegister;
    }
}
