package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Product> myProducts = new ArrayList<>();
        Product handy = new Product("Handy", "1");
        Product laptop = new Product("Laptop", "2");
        Product pencil = new Product("Bleistift", "3");
        Product cup = new Product("Tasse", "5");
        myProducts.add(handy);
        myProducts.add(laptop);
        myProducts.add(pencil);
        System.out.println(myProducts);
        System.out.println("------------------------------------------");

        ProductRepo myProdRepo = new ProductRepo(myProducts);
        Product candle = new Product("Kerze", "4");
        myProdRepo.addProduct(candle);
        System.out.println(myProdRepo);
        System.out.println("------------------------------------------");

        myProdRepo.removeProduct(candle);
        System.out.println(myProdRepo);
        System.out.println("------------------------------------------");

        System.out.println(myProdRepo.containsProduct(candle));
        System.out.println(myProdRepo.containsProduct(handy));
        System.out.println("------------------------------------------");

        List<Order> myOrders = new ArrayList<>();
        Order order1 = new Order(handy, "1", OrderStatus.PROCESSING, ZonedDateTime.now());
        Order order2 = new Order(candle, "2", OrderStatus.PROCESSING, ZonedDateTime.now());
        Order order3 = new Order(cup, "3", OrderStatus.PROCESSING, ZonedDateTime.now());
        Order order4 = new Order(laptop, "4", OrderStatus.PROCESSING, ZonedDateTime.now());
        myOrders.add(order1);
        myOrders.add(order2);
        System.out.println(myOrders);
        System.out.println("------------------------------------------");

        OrderListRepo myOrderRepo = new OrderListRepo();
        System.out.println(myOrderRepo);
        System.out.println("------------------------------------------");

        ShopService service = new ShopService(myProdRepo, myOrderRepo);
        service.placeOrder(order1, handy, "1");
        System.out.println(myOrderRepo);
        service.placeOrder(order3, cup, "3");
        System.out.println(myOrderRepo);
        System.out.println("------------------------------------------");

        Map<Order, String> myMapOrders = new HashMap<>();
        myMapOrders.put(order4, "1");
        myMapOrders.put(order1, "2");
        myMapOrders.put(order2, "3");
        System.out.println(myMapOrders);

        OrderMapRepo myMapOrderRepo = new OrderMapRepo();
        myMapOrderRepo.addOrder(order4);
        myMapOrderRepo.addOrder(order1);
        System.out.println(myMapOrderRepo);

        Stream<String> lines = Files.lines(Path.of("transactions.txt"));
        List<String> textLines = lines.toList();
        textLines.stream()
                .filter(line -> !line.isEmpty())
                .map(line -> {
                    String[] linesSplit = line.split(" ");
                    return linesSplit[0];
                })
                .forEach(System.out::println);
    }
}