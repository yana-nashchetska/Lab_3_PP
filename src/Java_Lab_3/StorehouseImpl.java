package Java_Lab_3;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class StorehouseImpl implements StorehouseService {
    public Check getLastCheck(Customer customer) {
        return customer.getMyChecks().isEmpty() ? null : customer.getMyChecks().get(customer.getMyChecks().size() - 1);
    }

    public void buy(Storehouse storehouse, Customer customer, LocalDateTime date, ProductInfo... args) {
        customer.addCheck(new Check(date,
                storehouse.getName(),
                customer.getFirstName(), customer.getLastName(),
                args));

        calcTotalSum(customer);
        recalculateQuantity(storehouse, customer, args);
        printCheck(customer);
    }

    @Override
    public void calcTotalSum(Customer customer) {
        // Отримати останній чек
        Check lastCheck = getLastCheck(customer);

        if (lastCheck != null) {
            // Отримати список продуктів з останнього чеку
            ArrayList<ProductInfo> products = lastCheck.getBoughtProducts();

            // Порахувати загальну суму
            double totalSum = products.stream()
                    .mapToDouble(productInfo -> productInfo.getProduct().getPrice() * productInfo.getQuantity())
                    .sum();

            // Встановити загальну суму
            lastCheck.setTotalSum(totalSum);
        } else {
            System.out.println("No checks to calculate total sum.");
        }

    }

    public void printCheck(Customer customer) {
        // Отримати останній чек
        Check lastCheck = getLastCheck(customer);

        if (lastCheck != null) {
            // Створити унікальне ім'я файлу для чеку
            String fileName = "check_" + customer.getFirstName() + "_" + customer.getLastName() + "_" + System.currentTimeMillis() + ".txt";
            Path checkPath = Paths.get("D:\\PP\\Labs\\Java_Lab_3\\Checks_java_3", fileName);
            this.addComment();
            this.addBag();

            try {
                // Записати чек у файл
                Files.write(checkPath, lastCheck.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

                // Зробити файл тільки для читання
                File file = checkPath.toFile();
                boolean isReadOnly = file.setReadOnly();
                if (isReadOnly) {
                    System.out.println("Check printed successfully and is now read-only!");
                } else {
                    System.out.println("Check printed successfully, but failed to make the file read-only.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No checks to print.");
        }
    }


    @Override
    public void printAllChecks(Storehouse storehouse) throws CustomException{
        if (storehouse.getAllCustomers().isEmpty()) {
            throw new CustomException("No customers to print checks.");
        }

        storehouse.getAllCustomers().forEach(customer -> {
            System.out.println(customer);  // Виводимо інформацію про покупця

            if (customer.getMyChecks().isEmpty()) {
                System.out.println("No checks to print for this customer.");
            } else {
                customer.getMyChecks().forEach(check -> {
                    System.out.println("Check: " + check);
                });
            }
        });
    }



    @Override
    public void recalculateQuantity(Storehouse storehouse, Customer customer, ProductInfo... args) {
        ArrayList<ProductInfo> temporary = new ArrayList<>(Arrays.asList(args));
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

       // System.out.println("Updated storehouse: " + storehouse.getAllProducts());
    }


    @Override
    public void addCustomer(Storehouse storehouse, Customer customer) {
        storehouse.getAllCustomers().add(customer);
    }

    @Override
    public void editStorehouse(Storehouse storehouse, Product product, int productPrice) {
        storehouse.getAllProducts().stream()
                .forEach(prInfStr -> {
            if (prInfStr.getProduct().getName().equals(product.getName())) {
                prInfStr.getProduct().setPrice(productPrice);
            }
        });
    }

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
