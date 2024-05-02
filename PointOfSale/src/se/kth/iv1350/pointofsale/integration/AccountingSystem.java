package se.kth.iv1350.pointofsale.integration;

import se.kth.iv1350.pointofsale.integration.DTO.ItemDTO;

import java.util.List;

/**
 * Represents the accounting system of the point of sale system, responsible for processing sale information.
 */
public class AccountingSystem {

    /**
     * Constructs a new AccountingSystem object.
     */
    public AccountingSystem() {}

    /**
     * Processes sale information.
     *
     * @param saleDTO The list of sold items containing sale information.
     */
    public void processSaleInformation(List<ItemDTO> saleDTO) {
        System.out.println("Sale information processed successfully in the accounting system.");
    }

}
