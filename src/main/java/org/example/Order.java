package org.example;

public record Order(
        Product product,
        String id,
        OrderStatus orderStatus) {

}
