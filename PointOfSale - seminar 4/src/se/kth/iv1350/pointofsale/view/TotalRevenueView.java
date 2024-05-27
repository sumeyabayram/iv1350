package se.kth.iv1350.pointofsale.view;

import se.kth.iv1350.pointofsale.model.SaleObserver;

/**
 * Class to show the total income of all sales on the user interface
 */
public class TotalRevenueView implements SaleObserver {
    
    private int totalRevenue;

    /**
     * Prints total revenue
     * @param totalPrice added to total revenue
     */
    @Override    
    public void priceOfSale (int totalPrice)
    {
        totalRevenue += totalPrice;
        System.out.println("Total revenue: " + totalRevenue + "\n");
    }

}
