package se.iths.jacob.webshop;

import se.iths.jacob.webshop.model.Product;

import java.util.List;

public interface ProductStorage {
    void saveProduct(Product product);

    List<Product> getProducts();

    Product getProduct(String articleNumber);
}