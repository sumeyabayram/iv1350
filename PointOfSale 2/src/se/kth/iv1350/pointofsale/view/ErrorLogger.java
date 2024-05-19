package se.kth.iv1350.pointofsale.view;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class handles logging error messages to a file.
 */
public class ErrorLogger {

     /**
     * Logs an error message to the error log file.
     * 
     * @param errorMessage The error message to be logged.
     */
    public static void logError(String errorMessage) {
        try (FileWriter writer = new FileWriter("error.log", true)) {
            writer.write(errorMessage + "\n");
        } catch (IOException e) {
            System.err.println("Failed to log error: " + e.getMessage());
        }
    }
}

