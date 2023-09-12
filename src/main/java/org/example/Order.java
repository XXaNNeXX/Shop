package org.example;

import lombok.With;

import java.time.ZonedDateTime;

@With
public record Order(
        Product product,
        String id,
        OrderStatus orderStatus,
        ZonedDateTime orderTime) {

}
