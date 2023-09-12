package org.example;

import java.util.List;

public class OrderListRepo implements OrderRepo {

    private List<Order> orders;

    public OrderListRepo() {
    }

    public OrderListRepo(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public Order addOrder(Order order, String id) {
        orders.add(order);
        return order;
    }
    @Override
    public Order removeOrder(Order order, String id) {
        orders.remove(order);
        return order;
    }
    @Override
    public boolean containsOrder(Order order) {
        return orders.contains(order);
    }

    @Override
    public String toString() {
        return "OrderListRepo{" +
                "orders=" + orders +
                '}';
    }
}
