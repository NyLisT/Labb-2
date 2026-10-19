package se.iths.jacob.webshop.model;

public class TenPercentDiscount extends Discount {

    public TenPercentDiscount() {

        super("10% Rabatt");
    }

    @Override
    public double calculatePrice(double originalPrice) {

        return originalPrice * 0.90;
    }
}
