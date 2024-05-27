package se.kth.iv1350.pointofsale.model;

import se.kth.iv1350.pointofsale.integration.DTO.ItemDTO;
import se.kth.iv1350.pointofsale.integration.DTO.SaleDTO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single sale made by one customer and paid with one payment.
 */
public class Sale {
    private LocalTime timeOfSale;
    private Payment payment;
    private Receipt receipt;
    private List<ItemDTO> saleItems;
    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Creates a new instance of the Sale class and saves the time of the sale.
     */
    public Sale() {
        timeOfSale = LocalTime.now();
        payment = new Payment();
        receipt = new Receipt(timeOfSale);
        saleItems = new ArrayList<>(); 
    }

    /**
     * Records the sale of an item with the specified quantity.
     *
     * @param item     The item being sold.
     * @param quantity The quantity of the item being sold.
     */
    public void recordSoldItem(ItemDTO item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            saleItems.add(item);
        }
    } 

    /**
     * Calculates the total price of the sale, including VAT.
     *
     * @return The total price of the sale.
     */
    public int calculateTotalPrice() {
        int totalPriceWithVAT = 0;
        for (ItemDTO item : saleItems) {
            if (item != null) {
                float priceWithVAT = (item.getPrice() * (1 + item.getVAT()));
                totalPriceWithVAT += priceWithVAT;
            }
        }
        return totalPriceWithVAT;
    }


    /**
     * Calculates the change to be returned to the customer after the sale.
     *
     * @return The change to be returned to the customer.
     */
    public int calculateChange(int customerPaid, int totalPrice) {
        return payment.calculateChange(customerPaid, totalPrice);
    }

    /**
     * Generates a receipt for the sale.
     */
    public void createReceipt() {
        SaleDTO saleInfo = retrieveSaleInfo();
        receipt.initiateReceipt(saleInfo);
        notifyObservers();
    }

    /**
     * Retrieves the information about the items sold in the sale.
     *
     * @return A saleDTO object representing the sold items.
     */
    public SaleDTO retrieveSaleInfo() {
        return new SaleDTO (saleItems);
    }

    /**
     * Adds observers to the SaleObserver list
     * 
     * @param obs The observer added
     */
    public void addSaleObserver(List<SaleObserver> obs) {
        saleObservers.addAll(obs);
    }

    /**
     * Calls SaleObserver
     */
    private void notifyObservers() {
        for (SaleObserver observer : saleObservers) {
            observer.priceOfSale(calculateTotalPrice());
        }
    }
}
