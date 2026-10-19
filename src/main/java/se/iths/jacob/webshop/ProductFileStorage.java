package se.iths.jacob.webshop;

import se.iths.jacob.webshop.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ProductFileStorage implements ProductStorage {
    private Path file = Path.of("products.txt");

    @Override
    public void saveProduct(Product product) {
        try {
            String line = product.toFileLine() + System.lineSeparator();
            Files.writeString(file, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            IO.println("Fel vid sparande till fil: " + e.getMessage());
        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        if (!Files.exists(file)) {
            return products;
        }

        try {
            List<String> lines = Files.readAllLines(file);
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    products.add(Product.fromFileLine(line));
                }
            }
        } catch (IOException e) {
            IO.println("Fel vid läsning från fil: " + e.getMessage());
        }

        return products;
    }

    @Override
    public Product getProduct(String articleNumber) {
        List<Product> products = getProducts();
        for (Product product : products) {
            if (product.getArticleNumber().equalsIgnoreCase(articleNumber)) {
                return product;
            }
        }
        return null;
    }
}