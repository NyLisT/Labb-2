package se.iths.jacob.webshop;

public class Main {
    static void main(String[] args) {

        ProductStorage storage = new ProductFileStorage();


        WebShopManager manager = new WebShopManager(storage);

        manager.startWebshop();
    }
}