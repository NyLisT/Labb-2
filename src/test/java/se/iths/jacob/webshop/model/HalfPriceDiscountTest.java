package se.iths.jacob.webshop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HalfPriceDiscountTest {

    @Test
    void calculatePriceShouldApplyHalfPriceDiscount() {

        Discount discount = new HalfPriceDiscount();
        
        double price = 100.0;

        double rabattPris = discount.calculatePrice(price);

        assertEquals(50.0, rabattPris, 0.0001);
    }
}