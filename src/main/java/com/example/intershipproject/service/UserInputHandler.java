package com.example.intershipproject.service;

import com.example.intershipproject.entites.Order;
import com.example.intershipproject.entites.OrderItems;
import com.example.intershipproject.entites.Product;
import com.example.intershipproject.entites.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class UserInputHandler {
    Scanner scanner = new Scanner(System.in);
    List<Product> allProducts = new ArrayList<>();
    boolean isOk = false;
    boolean isDone = false;
    boolean isOneMore = false;

    @Value("${remove.password}")
    String password;

    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    public void handler() {
        allProducts = productService.getAllProducts();
        boolean isQuit = false;
        String scanLine = scanner.nextLine();
        while (!scanLine.equals("quit")) {
            isOk = false;
            switch (scanLine) {
                case "createProduct":
                    createProduct();
                    break;
                case "showAll":
                    showAllProducts();
                    break;
                case "showAllOrdered":
                    showAllOrderedProducts();
                    break;
                case "createOrder":
                    createOrder();
                    break;
                case "updateOrder":
                    updateOrder();
                    break;
                case "showAllOrders":
                    showAllOrders();
                    break;
                case "help":
                    showHelp();
                    break;
                case "removeProduct":
                    removeProductById();
                    break;
                case "removeAllProducts":
                    removeAllProducts();
                    break;
            }
            if (!isOk) {
                System.out.println("Enter valid command:");
                scanLine = scanner.nextLine();
            } else {
                System.out.println("What's next?");
                scanLine = scanner.nextLine();
            }
        }
    }

    @Transactional()
    public void createProduct() {
        System.out.println("You want to create a product! Please, enter the name and price of product separated by coma");
        isDone = false;
        String prodLine = scanner.nextLine();
        while (!isDone || !prodLine.equals("quit")) {
            String[] productSplit = prodLine.split(",");
            if (productSplit.length == 2 && (numberChecker(productSplit[1]))) {
                Product product = new Product();
                product.setStatus(ProductStatus.IN_STOCK);
                product.setName(productSplit[0]);
                product.setPrice(Integer.parseInt(productSplit[1]));
                product.setCreatedAt(LocalDateTime.now());
                productService.saveProduct(product);
                allProducts = productService.getAllProducts();
                System.out.println("Product created and saved");
                isDone = true;
                isOk = true;
                break;
            }

            System.out.println("Please, enter the name and price of product separated by coma. Example: magazine,10");
            prodLine = scanner.nextLine();
        }

    }

    public void showAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        allProducts.forEach(p -> {
            System.out.println(p.getName() + ", price = " + p.getPrice() + ", status = " + p.getStatus());
        });
        isOk = true;
    }

    public void showAllOrderedProducts() {
        List<Object[]> products = productService.allOrderedProducts();
        for (Object[] product : products) {
            System.out.println("Product name: " + product[0] + ", quantity: " + product[1]);
        }
        isOk = true;
    }

    public void createOrder() {
        System.out.println("You are going to create an order. Select product id to add to order");
        isDone = false;

        for (int i = 0; i < allProducts.size(); ) {
            for (int j = 0; (j < 4 && i < allProducts.size()); j++, i++) {
                System.out.print("id: " + allProducts.get(i).getId() + ", name: " + allProducts.get(i).getName() + "; \t");
            }
            System.out.println();
        }
        String orderLine = scanner.nextLine();
        Order order = new Order();

        while (!isDone || !orderLine.equals("quit")) {
            OrderItems oi = new OrderItems();
            if (numberChecker(orderLine)) {
                order.setStatus("IN PROGRESS");
                order.setCreatedAt(LocalDateTime.now());
                order.setUserId((int) Math.floor(Math.random() * 100));
                if (!productService.getProductById(Integer.parseInt(orderLine)).isPresent()) {
                    System.out.println("Wrong product id");
                } else {
                    oi.setOrder(order);
                    oi.setProduct(productService.getProductById(Integer.parseInt(orderLine)).get());
                    System.out.println("Set quantity");
                    orderLine = scanner.nextLine();
                    while (!orderLine.equals("quit")) {
                        if (numberChecker(orderLine)) {
                            oi.setQuantity(Integer.parseInt(orderLine));
                            break;
                        } else {
                            System.out.println("Enter correct number");
                            orderLine = scanner.nextLine();
                        }
                    }
                    orderService.saveOrder(order);
                    orderItemService.saveOrderItem(oi);
                }
                isOneMore = false;
                while (!isOneMore) {
                    System.out.println("Do you want to add one more product? y/n:");
                    orderLine = scanner.nextLine();
                    if (orderLine.equals("y")) {
                        break;
                    } else if (orderLine.equals("n")) {
                        isDone = true;
                        isOneMore = true;
                        break;
                    }
                }
            }

            if (!isDone) {
                System.out.println("Please, enter correct product id.");
                for (int i = 0; i < allProducts.size(); i++) {
                    for (int j = 0; (j < 4 && i < allProducts.size()); j++, i++) {
                        System.out.print("id: " + allProducts.get(i).getId() + ", name: " + allProducts.get(i).getName() + "; \t");
                    }
                    System.out.println();
                }
                orderLine = scanner.nextLine();
            } else {
                break;
            }

        }
        isOk = true;

    }

    public void updateOrder() {
        List<Order> allOrders = orderService.getAllOrders();
        System.out.println("You are going to update an order. Select order id to update");
        isDone = false;

//        List<OrderItems> allOrderItems = orderItemService.getAllOrderItems();
        for (Order allOrderItem : allOrders) {
            System.out.println("order id - " + allOrderItem.getId());
        }
        String updateOrderLine = scanner.nextLine();

        while (!isDone || !updateOrderLine.equals("quit")) {
            if (numberChecker(updateOrderLine)) {
                if (!orderService.getOrderById(Integer.parseInt(updateOrderLine)).isPresent()) {
                    System.out.println("Select existing order");
                } else {
                    Order order = orderService.getOrderById(Integer.parseInt(updateOrderLine)).get();
                    System.out.println("What product do you want to add? Select id");
                    for (int i = 0; i < allProducts.size(); i++) {
                        for (int j = 0; (j < 4 && i < allProducts.size()); j++, i++) {
                            System.out.print("id: " + allProducts.get(i).getId() + ", name: " + allProducts.get(i).getName() + "; \t");
                        }
                        System.out.println();
                    }
                    String productLine = scanner.nextLine();
                    while (!isDone || !productLine.equals("quit")) {
                        OrderItems oi = new OrderItems();
                        if (numberChecker(productLine)) {
                            if (!productService.getProductById(Integer.parseInt(productLine)).isPresent()) {
                                System.out.println("Wrong product id");
                            } else {
                                oi.setOrder(order);
                                oi.setProduct(productService.getProductById(Integer.parseInt(productLine)).get());
                                System.out.println("Set quantity");
                                productLine = scanner.nextLine();
                                while (!productLine.equals("quit")) {
                                    if (numberChecker(productLine)) {
                                        oi.setQuantity(Integer.parseInt(productLine));
                                        break;
                                    } else {
                                        System.out.println("Enter correct number");
                                        productLine = scanner.nextLine();
                                    }
                                }
                                orderService.saveOrder(order);
                                orderItemService.saveOrderItem(oi);
                                isDone = true;
                                isOk = true;
                            }
                        }

                        if (!isDone) {
                            System.out.println("Please, enter correct product id.");
                            productLine = scanner.nextLine();
                        } else {
                            break;
                        }

                    }


                }
            }
            if (!isDone) {
                System.out.println("Enter correct id!");
                updateOrderLine = scanner.nextLine();
            } else break;
        }
    }

    public void showAllOrders() {
        List<Object[]> entireInfoAboutEveryOrder = orderService.getEntireInfoAboutEveryOrder();
        for (Object[] object : entireInfoAboutEveryOrder) {
            System.out.println("Order id: " + object[0] + ", product name: " + object[1] + ", total price: " + object[2] +
                    ", quantity in order: " + object[3] + ", orded creation: " + object[4]);
        }
        isOk = true;
    }

    public void removeProductById() {
        System.out.println("You are going to remove product. Select an id of product you want to remove");
        for (int i = 0; i < allProducts.size(); i++) {
            for (int j = 0; (j < 4 && i < allProducts.size()); j++, i++) {
                System.out.print("id: " + allProducts.get(i).getId() + ", name: " + allProducts.get(i).getName() + "; \t");
            }
            System.out.println();
        }
        isDone = false;
        String removeLine = scanner.nextLine();
        while (!isDone && !removeLine.equals("quit")) {
            if (numberChecker(removeLine)) {
                if (!productService.getProductById(Integer.parseInt(removeLine)).isPresent()) {
                    System.out.println("Wrong product id");
                } else {
                    productService.removeById(Integer.parseInt(removeLine));
                    System.out.println("Successfully deleted");
                    isDone = true;
                }
            }
            isOk = true;
        }
    }

    public void removeAllProducts() {
        System.out.println("You are going to remove all the products. Enter password to continue:");
        String removeAll = scanner.nextLine();
        if (removeAll.equals(password)) {
            productService.removeAllProducts();
            System.out.println("All the products are deleted");
        } else {
            System.out.println("Password is wrong!");
        }
        isOk = true;
    }

    public void showHelp() {
        System.out.println("createProduct   - allows you to create new Product and save it to database;");
        System.out.println("showAll         - shows all created products;");
        System.out.println("showAllOrdered  - shows all the products that have ever been ordered;");
        System.out.println("createOrder     - allows to create a new Order");
        System.out.println("updateOrder     - helps to update existing order by id");
        System.out.println("showAllOrders   - shows detail info about every order, product and prices");
        System.out.println("help            - shows available commands");
    }

    public boolean numberChecker(String string) {
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int temp = chars[i];
            if (temp < 48 || temp > 57) return false;
        }
        return true;
    }
}
