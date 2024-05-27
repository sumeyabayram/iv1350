package se.kth.iv1350.pointofsale.model;

/**
 * Interface for observing new sales
 */
public interface SaleObserver {
    /**
     * Invoked when a sale is concluded
     * @param totalPrice Total price of sale
     */
    void priceOfSale(int totalPrice);
}
