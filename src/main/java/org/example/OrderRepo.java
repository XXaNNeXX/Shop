package org.example;

import java.util.List;
import java.util.Map;

public interface OrderRepo {

    List<Order> getOrders();
    Order getOrderById(String id);
    Order addOrder(Order order);
    void removeOrder(String id);
    String toString();
}
