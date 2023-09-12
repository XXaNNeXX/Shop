package org.example;

import java.util.List;

public class ProductRepo {

    private List<Product> products;

    public ProductRepo() {
    }

    public ProductRepo(List<Product> products) {
        this.products = products;
    }

    public List<Product> addProduct(Product product) {
        products.add(product);
        return products;
    }

    public List<Product> removeProduct(Product product) {
        products.remove(product);
        return products;
    }

    public boolean containsProduct(Product product) {
        return products.contains(product);
    }

    @Override
    public String toString() {
        return "ProductRepo{" +
                "products=" + products +
                '}';
    }
}
