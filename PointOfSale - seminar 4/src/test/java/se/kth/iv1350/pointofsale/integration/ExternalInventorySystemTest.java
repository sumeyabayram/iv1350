package test.java.se.kth.iv1350.pointofsale.integration;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.pointofsale.integration.DatabaseFailureException;
import se.kth.iv1350.pointofsale.integration.ExternalInventorySystem;
import se.kth.iv1350.pointofsale.integration.InvalidIdentifierException;
import se.kth.iv1350.pointofsale.integration.DTO.ItemDTO;
import se.kth.iv1350.pointofsale.integration.DTO.SaleDTO;

public class ExternalInventorySystemTest {

    private ExternalInventorySystem externalInventorySystem;

    @Before
    public void setUp() {
        externalInventorySystem = new ExternalInventorySystem();
    }

    @Test
    public void testRetrieveItemExisting() throws InvalidIdentifierException, DatabaseFailureException {
        ExternalInventorySystem inventorySystem = new ExternalInventorySystem();
    String existingItemID = "987654";

    try {
        ItemDTO item = inventorySystem.retrieveItem(existingItemID);
        assertNotNull("Item should not be null", item);
        assertEquals("987654", item.getItemIdentifier());
        assertEquals("AirPods Max", item.getName());
    } catch (InvalidIdentifierException | DatabaseFailureException e) {
        fail("Unexpected exception occurred: " + e.getMessage());
    }
}

    @Test
    public void testRetrieveItemInvalidIdentifierException() {
        ExternalInventorySystem inventorySystem = new ExternalInventorySystem();
        String nonExistingItemID = "9999999999";

        try {
            ItemDTO result = inventorySystem.retrieveItem(nonExistingItemID);
            fail("Expected InvalidIdentifierException was not thrown");
        } catch (InvalidIdentifierException e) {
            assertEquals(nonExistingItemID + " does not exist in the inventory catalog.", e.getMessage());
        } catch (DatabaseFailureException e) {
            fail("Unexpected DatabaseFailureException was thrown: " + e.getMessage());
        }
    }

    @Test
    public void testRetrieveItemDatabaseFailureException() {
        ExternalInventorySystem inventorySystem = new ExternalInventorySystem();
        String invalidItemID = "111";

        try {
            ItemDTO result = inventorySystem.retrieveItem(invalidItemID);
            fail("Expected DatabaseFailureException was not thrown");
        } catch (DatabaseFailureException e) {
            assertEquals("Unable to establish connection to the database.", e.getMessage());
        } catch (InvalidIdentifierException e) {
            fail("Unexpected InvalidIdentifierException was thrown: " + e.getMessage());
        }
    }

    @Test
    public void testSaleInformation() {
        List<ItemDTO> saleItems = Arrays.asList
        (   new ItemDTO("987654", "TestItem1", 100, 0.1f, 5),
            new ItemDTO("654321", "TestItem2", 200, 0.2f, 10),
            new ItemDTO("246810", "TestItem3", 300, 0.3f, 20));
        SaleDTO saleDTO = new SaleDTO(saleItems);
        externalInventorySystem.saleInformation(saleDTO);
    }
}

