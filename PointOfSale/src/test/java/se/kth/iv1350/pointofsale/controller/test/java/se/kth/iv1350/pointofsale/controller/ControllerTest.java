package test.java.se.kth.iv1350.pointofsale.controller.test.java.se.kth.iv1350.pointofsale.controller;

import se.kth.iv1350.pointofsale.controller.Controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pointofsale.integration.DTO.ItemDTO;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void setUp() {
        controller = new Controller();
        controller.newSale(); 
    }

    @Test
    public void testRegisterItem() {
        ItemDTO item = controller.registerItem("987654", 10);
        assertNotNull(item);
        assertEquals("AirPods Max", item.getName());
        assertEquals(10, item.getQuantity());
    }
}
