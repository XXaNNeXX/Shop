package org.example;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String productId) {
        super("Product mit der ID " + productId + " existiert nicht");
    }
}
