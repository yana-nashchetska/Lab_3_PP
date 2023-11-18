package Java_Lab_3;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class StorehouseImpl  implements StorehouseService {

        /* storehouse.getCustomers().stream()
                .filter(c -> c.getName().equals(customer.getName())
                        && c.getSurname().equals(customer.getSurname()))
                .findFirst()
                .ifPresentOrElse(
                        c -> {
                            c.getChecks().add(new Check(LocalDateTime.now(), new ArrayList<>(Arrays.asList(order))));
                        },
                        () -> {
                            customer.getChecks().add(new Check(LocalDateTime.now(), new ArrayList<>(Arrays.asList(order))));
                            storehouse.getCustomers().add(customer);
                        }
                );
        storehouse.getCustomers().stream()
                .filter(c -> c.getName().equals(customer.getName())
                        && c.getSurname().equals(customer.getSurname()))
                .findFirst()
                .ifPresentOrElse(
                        c -> {
                            c.getChecks().stream()
                                    .filter(ch -> ch.getDateOfPurchase().equals(date))
                                    .findFirst()
                                    .ifPresentOrElse(
                                            ch -> {
                                                ch.getOrders().add(order);
                                            },
                                            () -> {
                                                c.getChecks().add(new Check(date, new ArrayList<>(Arrays.asList(order))));
                                            }
                                    );
                        },
                        () -> {
                            customer.getChecks().add(new Check(date, new ArrayList<>(Arrays.asList(order))));
                            storehouse.getCustomers().add(customer);
                        }
                );
        storehouse.getAllProducts().stream()
                .filter(p -> p.getProduct().getName().equals(order.getProduct().getName()))
                .findFirst()
                .ifPresentOrElse(
                        p -> {
                            p.setQuantity(p.getQuantity() - order.getQuantity());
                        },
                        () -> {
                            System.out.println("There is no such product in the storehouse");
                        });*/

    @Override
    public void printCheck() {

    }

    public void buy(Storehouse storehouse, Customer customer, LocalDateTime date, ProductInfo... args) {
        customer.getMyChecks().add(new Check(date,
                storehouse.getName(),
                customer.getFirstName(), customer.getLastName(),
                args));

        recalculateQuantity(storehouse, customer, args);
    }

    @Override
    public void recalculateQuantity(Storehouse storehouse, Customer customer, ProductInfo... args) {
        ArrayList<ProductInfo> temporary = new ArrayList<>(Arrays.asList(args));

        System.out.println("Temporary purchase: " + temporary);

        storehouse.getAllProducts().forEach(productInfoInStorehouse -> {
            // Перевіряємо, чи продукт знаходиться в temporary за іменем
            Optional<ProductInfo> matchingProductInfo = temporary.stream()
                    .filter(temporaryProductInfo ->
                            temporaryProductInfo.getProduct().getName().equals(productInfoInStorehouse.getProduct().getName()))
                    .findFirst();

            matchingProductInfo.ifPresent(temporaryProductInfo -> {
                // Оновлюємо кількість в storehouse на основі temporary
                int newQuantity = productInfoInStorehouse.getQuantity() - temporaryProductInfo.getQuantity();
                productInfoInStorehouse.setQuantity(newQuantity);
            });
        });

        System.out.println("Updated storehouse: " + storehouse.getAllProducts());
    }


    @Override
    public void addCustomer(Storehouse storehouse, Customer customer) {

    }

    @Override
    public void editStorehouse(Storehouse storehouse, ProductInfo productInfo, int productPrice) {

    }


  /*  @Override
    public void recalculateQuantity(Storehouse storehouse, void printCheck) {

    }*/



    @Override
    public void addBag() {

    }

    @Override
    public void addComment() {

    }

    @Override
    public void averagePrice() {

    }

    @Override
    public void spentMoney(LocalDateTime from, LocalDateTime to, String name, String surname) {
    }
}
