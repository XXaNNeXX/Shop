package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepo {

    private Map<String, Order> orderMap;

       @Override
    public List<Order> getOrders() {
        return new ArrayList<>(orderMap.values());
    }

    public Order getOrderById(String id) {
        return orderMap.get(id);
    }

    @Override
    public Order addOrder(Order order) {
        orderMap.put(order.id(), order);
        return order;
    }

    @Override
    public void removeOrder(String id) {
        orderMap.remove(id);
    }

   @Override
    public String toString() {
        return "OrderMapRepo{" +
                "orderMap=" + orderMap +
                '}';
    }
}
