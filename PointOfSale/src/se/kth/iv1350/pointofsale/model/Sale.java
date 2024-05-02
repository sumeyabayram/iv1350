package se.kth.iv1350.pointofsale.model;

import se.kth.iv1350.pointofsale.integration.DTO.ItemDTO;
import se.kth.iv1350.pointofsale.integration.DTO.SaleDTO;

import java.time.LocalTime;
import java.util.List;

/**
 * Represents a single sale made by one customer and paid with one payment.
 */
public class Sale {
    private LocalTime timeOfSale;
    private Payment payment;
    private Receipt receipt;
    private SaleDTO saleDTO;

    /**
     * Creates a new instance of the Sale class and saves the time of the sale.
     */
    public Sale() {
        timeOfSale = LocalTime.now();
        payment = new Payment();
        receipt = new Receipt(timeOfSale);
        saleDTO = new SaleDTO();
    }

    /**
     * Records the sale of an item with the specified quantity.
     *
     * @param item     The item being sold.
     * @param quantity The quantity of the item being sold.
     */
    public void recordSoldItem(ItemDTO item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.saleDTO.addSoldItem(item);
        }
    }

    /**
     * Calculates the total price of the sale, including VAT.
     *
     * @return The total price of the sale.
     */
    public int calculateTotalPrice() {
        int totalPriceWithVAT = 0;
        for (ItemDTO item : saleDTO.getSoldItems()) {
            if (item != null) {
                float priceWithVAT = (item.getPrice() * (1 + item.getVAT()));
                totalPriceWithVAT += priceWithVAT;
            } else {
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
      receipt.initiateReceipt(saleDTO);
    }

    /**
     * Retrieves the information about the items sold in the sale.
     *
     * @return A list of ItemDTO objects representing the sold items.
     */
    public List<ItemDTO> retrieveSaleInfo() {
        return saleDTO.getSoldItems();
    }
}
