package org.example;

import java.util.Map;

public class OrderMapRepo implements OrderRepo {

    private Map<Order, String> orderMap;

    public OrderMapRepo() {
    }

    public OrderMapRepo(Map<Order, String> orderMap) {
        this.orderMap = orderMap;
    }

    @Override
    public Order addOrder(Order order, String id) {
        orderMap.put(order, id);
        return order;
    }

    @Override
    public Order removeOrder(Order order, String id) {
        orderMap.remove(order, id);
        return order;
    }

    @Override
    public boolean containsOrder(Order order) {
        return orderMap.containsKey(order);

    }

    @Override
    public String toString() {
        return "OrderMapRepo{" +
                "orderMap=" + orderMap +
                '}';
    }
}
