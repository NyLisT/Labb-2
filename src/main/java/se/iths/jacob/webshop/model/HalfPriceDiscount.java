package se.iths.jacob.webshop.model;

public class HalfPriceDiscount extends Discount {

    public HalfPriceDiscount() {
        super("Halva Priset Rabatt");
    }

    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.50;
    }
}
