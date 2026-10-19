package se.iths.jacob.webshop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TenPercentDiscountTest {

    @Test
    void calculatePriceShouldApplyTenPercentDiscount() {
        
        Discount discount = new TenPercentDiscount();

        double price = 100.0;

        double rabattPris = discount.calculatePrice(price);

        assertEquals(90.0, rabattPris, 0.0001);
    }
}