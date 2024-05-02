package test.java.se.kth.iv1350.pointofsale.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.List;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.pointofsale.integration.ExternalInventorySystem;
import se.kth.iv1350.pointofsale.integration.DTO.ItemDTO;

public class ExternalInventorySystemTest {

    private ExternalInventorySystem externalInventorySystem;

    @Before
    public void setUp() {
        externalInventorySystem = new ExternalInventorySystem();
    }

    @Test
    public void testRetrieveItemExisting() {
        ItemDTO item = externalInventorySystem.retrieveItem("987654");
        assertNotNull("Item should not be null", item);
        assertEquals("987654", item.getItemIdentifier());
        assertEquals("AirPods Max", item.getName());
    }

    @Test
    public void testRetrieveItemNonExisting() {
        ItemDTO nonExistingItem = externalInventorySystem.retrieveItem("123456");
        assertNull("Non-existing item should return null", nonExistingItem);
    }

    @Test
    public void testSaleInformation() {
        ItemDTO item1 = new ItemDTO("987654", "TestItem1", 100, 0.1f, 5);
        ItemDTO item2 = new ItemDTO("654321", "TestItem2", 200, 0.2f, 10);
        ItemDTO item3 = new ItemDTO("246810", "TestItem3", 300, 0.3f, 20);
        
        List<ItemDTO> saleDTO = Arrays.asList(item1, item2, item3);
        externalInventorySystem.saleInformation(saleDTO);
    }
}

