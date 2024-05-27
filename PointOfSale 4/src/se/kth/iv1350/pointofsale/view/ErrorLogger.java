package se.kth.iv1350.pointofsale.view;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class handles logging error messages to a file.
 */
public class ErrorLogger {
    private FileWriter writer;

    /**
     * Initializes a new instance of the ErrorLogger class.
     * Attempts to open the error log file for appending.
     */
    public ErrorLogger() {
        try {
            writer = new FileWriter("error.log", true);
        } catch (IOException e) {
            System.err.println("Failed to initialize the error logger: " + e.getMessage());
        }
    }

    /**
     * Logs an error message to the error log file.
     *
     * @param errorMessage The error message to be logged.
     */
    public void logError(String errorMessage) {
        try {
            if (writer != null) {
                writer.write(errorMessage + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            System.err.println("Failed to log error: " + e.getMessage());
        }
    }
}