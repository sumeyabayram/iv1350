package se.kth.iv1350.pointofsale.integration;

/**
 * Exception is thrown to indicate that the database cannot be called.
 */
public class DatabaseFailureException extends Exception {

    public DatabaseFailureException() {
        super ("Unable to establish connection to the database.");
    }
}
