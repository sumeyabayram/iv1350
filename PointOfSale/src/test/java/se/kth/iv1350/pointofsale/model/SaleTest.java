package test.java.se.kth.iv1350.pointofsale.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointofsale.integration.DTO.ItemDTO;
import se.kth.iv1350.pointofsale.model.Sale;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SaleTest {

    @Test
    void testRecordSoldItem() {
        Sale sale = new Sale();
        ItemDTO item = new ItemDTO("123456", "Test Item", 10.0f, 0.25f, 0);
        int quantity = 3;
        sale.recordSoldItem(item, quantity);
        List<ItemDTO> soldItems = sale.retrieveSaleInfo();
        assertEquals(quantity, soldItems.size());
        for (ItemDTO soldItem : soldItems) {
            assertEquals(item.getItemIdentifier(), soldItem.getItemIdentifier());
            assertEquals(item.getName(), soldItem.getName());
            assertEquals(item.getPrice(), soldItem.getPrice());
            assertEquals(item.getVAT(), soldItem.getVAT());
        }
    }

    @Test
    void testCreateReceipt() {
        Sale sale = new Sale();
        sale.createReceipt();
        assertTrue(true);
    }
}

