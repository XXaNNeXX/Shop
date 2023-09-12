package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ShopService {

    private ProductRepo productRepo;
    private OrderListRepo orderRepo;
    private OrderMapRepo orderMapRepo;

    public ShopService() {
    }

    public ShopService(ProductRepo productRepo, OrderListRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }
    public ShopService(ProductRepo productRepo, OrderMapRepo orderMapRepo) {
        this.productRepo = productRepo;
        this.orderMapRepo = orderMapRepo;
    }

    public void placeOrder(Order order, Product product, String id) {

        if(!productRepo.containsProduct(product)) {
            System.out.println("Das gewünschte Produkt " + product + " ist nicht verfügbar");
        } else {
            System.out.println("Deine Bestellung war erfolgreich");
            orderRepo.addOrder(order, id);
            orderMapRepo.addOrder(order, id);
        }
    }

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        try {
            for (String productId : productIds) {
                Optional<Product> productToOrder = productRepo.getProductById(productId);
                if (productToOrder.isEmpty()) {
                    //System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                    throw new ProductNotFoundException(productId);
                }
                products.add(productToOrder.get());
            }
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Order newOrder = new Order(new Product("Laptop", "2"), "1", OrderStatus.PROCESSING);

        return orderRepo.addOrder(newOrder, "1");
    }

    public List<Order> orderStatus(OrderStatus status) {
        return orderRepo.getOrders().stream()
                .filter(order -> order.orderStatus() == status)
                .toList();
    }
}
