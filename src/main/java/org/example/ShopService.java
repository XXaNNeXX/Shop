package org.example;

import java.util.List;

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
}
