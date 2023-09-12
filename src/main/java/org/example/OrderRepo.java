package org.example;

import java.util.Map;

public interface OrderRepo {

    Order addOrder(Order order, String id);
    Order removeOrder(Order order, String id);
    boolean containsOrder(Order order);
}
