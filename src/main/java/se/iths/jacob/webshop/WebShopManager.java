package se.iths.jacob.webshop;

import se.iths.jacob.webshop.model.Discount;
import se.iths.jacob.webshop.model.HalfPriceDiscount;
import se.iths.jacob.webshop.model.Product;
import se.iths.jacob.webshop.model.TenPercentDiscount;

import java.util.List;

public class WebShopManager {
    private ProductStorage productStorage;

    public WebShopManager(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public void saveProductToStorage(Product product) {
        productStorage.saveProduct(product);
    }

    public List<Product> getProductsFromStorage() {
        return productStorage.getProducts();
    }

    public Product getProductFromStorage(String articleNumber) {
        return productStorage.getProduct(articleNumber);
    }

    public void startWebshop() {
        String discountCode = IO.readln("Mata in rabattkod: ");
        Discount discount;

        if ("halfprice".equalsIgnoreCase(discountCode.trim())) {
            discount = new HalfPriceDiscount();
        } else {
            discount = new TenPercentDiscount();
        }

        IO.println("Aktiv rabatt: " + discount.getDescription());

        boolean running = true;
        while (running) {
            printMenu();
            String choice = IO.readln("Välj ett alternativ (1-4): ");

            switch (choice) {
                case "1" -> addProduct();
                case "2" -> listProducts(discount);
                case "3" -> showProductInfo(discount);
                case "4" -> {
                    IO.println("Avslutar applikationen...");
                    running = false;
                }
                default -> IO.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private void printMenu() {
        IO.println("--- MENY ---");
        IO.println("1. Lägg till produkt");
        IO.println("2. Lista alla produkter");
        IO.println("3. Visa information om en produkt");
        IO.println("4. Avsluta");
    }

    private void addProduct() {
        String articleNumber = IO.readln("Mata in artikelnummer: ");
        String title = IO.readln("Mata in titel: ");

        double price = 0.0;
        try {
            price = Double.parseDouble(IO.readln("Mata in pris: "));
        } catch (NumberFormatException e) {
            IO.println("Felaktigt prisformat. Produkten sparades inte.");
            return;
        }

        String description = IO.readln("Mata in beskrivning: ");

        Product product = new Product(articleNumber, title, price, description);
        saveProductToStorage(product);
        IO.println("Produkten sparades framgångsrikt!");
    }

    private void listProducts(Discount discount) {
        List<Product> products = getProductsFromStorage();
        if (products.isEmpty()) {
            IO.println("Inga produkter finns sparade.");
            return;
        }

        IO.println("=== Alla Produkter ===");
        for (Product p : products) {
            double discountedPrice = discount.calculatePrice(p.getPrice());
            IO.println("Art.nr: " + p.getArticleNumber() +
                    " | Titel: " + p.getTitle() +
                    " | Ord.pris: " + p.getPrice() + " kr" +
                    " | Rabatterat pris: " + discountedPrice + " kr" +
                    " | Beskrivning: " + p.getDescription());
        }
    }

    private void showProductInfo(Discount discount) {
        String articleNumber = IO.readln("Mata in artikelnummer: ");

        Product product = getProductFromStorage(articleNumber);

        if (product != null) {
            double discountedPrice = discount.calculatePrice(product.getPrice());
            IO.println("--- Produktinformation ---");
            IO.println(product.toString());
            IO.println("Rabatterat pris: " + discountedPrice + " kr");
        } else {
            IO.println("Produkten med artikelnummer '" + articleNumber + "' hittades inte.");
        }
    }
}