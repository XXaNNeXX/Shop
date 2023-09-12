package org.example;

import lombok.With;

@With
public record Order(
        Product product,
        String id,
        OrderStatus orderStatus) {

}
