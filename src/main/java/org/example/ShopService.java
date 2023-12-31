package org.example;

import java.time.ZonedDateTime;
import java.util.*;

public class ShopService {

    private ProductRepo productRepo;
    private OrderListRepo orderRepo;
    private OrderMapRepo orderMapRepo;
    private IdService uuid;

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
            orderRepo.addOrder(order);
            orderMapRepo.addOrder(order);
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

        Order newOrder = new Order(new Product("Laptop", "2"), "1", OrderStatus.PROCESSING, ZonedDateTime.now());

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> orderStatus(OrderStatus status) {
        return orderRepo.getOrders().stream()
                .filter(order -> order.orderStatus() == status)
                .toList();
    }

    /*public Order updateOrder(String orderID, Bestellstatus status) {
        Order update = orderRepo.getOrderById(orderID);
        return update.withOrderStatus(status);
    }*/

    public void updateOrder(String orderID, OrderStatus status) {
        Order update = orderRepo.getOrderById(orderID).withOrderStatus(status);
        orderRepo.removeOrder(orderID);
        orderRepo.addOrder(update);
    }

    public Map<OrderStatus, Order> getOldestOrderPerStatus(List<Order> orders) {
        Map<OrderStatus, Order> orderMap = new HashMap<>();

        for (Order order : orders) {
            OrderStatus status = order.orderStatus();
            ZonedDateTime time = order.orderTime();

            if (!orderMap.containsKey(status)
                    || time.isBefore(orderMap.get(status).orderTime())) {
                orderMap.put(status, order);
            }
        }

        return orderMap;
    }
}
