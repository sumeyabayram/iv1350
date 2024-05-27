package se.kth.iv1350.pointofsale.controller;

import se.kth.iv1350.pointofsale.model.Sale;
import se.kth.iv1350.pointofsale.model.SaleObserver;
import se.kth.iv1350.pointofsale.integration.DTO.ItemDTO;
import se.kth.iv1350.pointofsale.integration.DTO.SaleDTO;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.pointofsale.integration.AccountingSystem;
import se.kth.iv1350.pointofsale.integration.DatabaseFailureException;
import se.kth.iv1350.pointofsale.integration.ExternalInventorySystem;
import se.kth.iv1350.pointofsale.integration.InvalidIdentifierException;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private Sale sale;
    private AccountingSystem accountingSystem;
    private ExternalInventorySystem externalInventorySystem;
    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Starts a new sale. This method must be called before during anything else during a sale.
     */
    public void newSale() {
        sale = new Sale();
        accountingSystem = new AccountingSystem();
        externalInventorySystem = new ExternalInventorySystem();
        this.sale.addSaleObserver(saleObservers);
    }

    /**
     * Registers an item in the ongoing sale.
     *
     * @param itemID   The identifier of the item being registered.
     * @param quantity The quantity of the item being registered.
     * @throws DatabaseFailureException
     * @throws InvalidIdentifierException
     */
    public ItemDTO registerItem(String itemID, int quantity) throws InvalidIdentifierException, DatabaseFailureException {
        ItemDTO item;
        try {
            item = externalInventorySystem.retrieveItem(itemID);
        } catch (InvalidIdentifierException e) {
            throw new InvalidIdentifierException(itemID);
        } catch (DatabaseFailureException e) {
            throw new DatabaseFailureException();
        }
        sale.recordSoldItem(item, quantity);
        return item;
    }

    /**
     * Retrieves the total price of the ongoing sale.
     *
     * @return The total price of the ongoing sale.
     */
    public int getTotalPrice() {
        return sale.calculateTotalPrice();
    }

    /**
     * Concludes the ongoing sale by calculating the change for the customer.
     *
     * @param payment The payment made by the customer.
     * @return The change to be returned to the customer.
     */
    public int concludeSale(int payment) {
        int totalPrice = getTotalPrice();
        return sale.calculateChange(payment, totalPrice);
    }

    /**
     * Creates a receipt for the completed sale.
     */
    public void generateReceipt() {
       sale.createReceipt();
    }

    /**
     * Sends the sale information to external systems.
     */
    public void sendSaleInfo() {
        SaleDTO saleItems = sale.retrieveSaleInfo();
        externalInventorySystem.saleInformation(saleItems);
        accountingSystem.processSaleInformation(saleItems);
    }

    /**
     * Adds observers to the SaleObserver list
     * @param obs The observer added
     */
    public void addSaleObserver(SaleObserver obs) {
        saleObservers.add(obs);
    }

}
