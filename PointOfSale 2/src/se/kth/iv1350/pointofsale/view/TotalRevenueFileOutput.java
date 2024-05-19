package se.kth.iv1350.pointofsale.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.kth.iv1350.pointofsale.model.SaleObserver;

public class TotalRevenueFileOutput implements SaleObserver {

    private double totalRevenue;
    private PrintWriter outputFile;

    /**
     * Creates a new instance of TotalRevenueFileOutput and initializes the output file.
     */
    public TotalRevenueFileOutput() {
        try {
            outputFile = new PrintWriter(new FileWriter("totalRevenue.txt"), true);
        } catch (IOException e) {
            System.err.println("Failed to create output file: " + e.getMessage());
        }
    }

    /**
     * Adds the total price of a sale to the total revenue and writes it to the file.
     *
     * @param totalPrice The total price of the sale.
     */
    @Override
    public void priceOfSale(int totalPrice) {
        totalRevenue += totalPrice;
        if (outputFile != null) {
            outputFile.println("Date: " + java.time.LocalDate.now() + " Total revenue: " + totalRevenue + "\n");
        } else {
            System.err.println("Output to file did not work.");
        }
    }
}
