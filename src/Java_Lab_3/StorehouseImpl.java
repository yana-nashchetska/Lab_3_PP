package Java_Lab_3;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StorehouseImpl implements StorehouseService {
    public Check getLastCheck(Customer customer) {
        return customer.getMyChecks().isEmpty() ? null : customer.getMyChecks().get(customer.getMyChecks().size() - 1);
    }

    public void buy(Storehouse storehouse, Customer customer, LocalDate date, ProductInfo... args) {
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
            try {
                // Записати чек у файл
                Files.write(checkPath, lastCheck.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

                // Зробити файл тільки для читання
               /* File file = checkPath.toFile();
                boolean isReadOnly = file.setReadOnly();
                if (isReadOnly) {
                    System.out.println("Check printed successfully and is now read-only!");
                } else {
                    System.out.println("Check printed successfully, but failed to make the file read-only.");
                }*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No checks to print.");
        }
    }


    @Override
    public void printAllChecks(Storehouse storehouse) throws CustomException {
        if (storehouse.getAllCustomers().isEmpty()) {
            throw new CustomException("No customers to print checks.");
        }

        storehouse.getAllCustomers().forEach(customer -> {
            System.out.println(customer);  // Виводимо інформацію про покупця

            if (customer.getMyChecks().isEmpty()) {
                System.out.println("No checks to print for this customer.");
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
    public double calcAveragePrice(Storehouse storehouse) {
        double averagePrice = storehouse.getAllProducts().stream()
                .mapToDouble(productInfo -> productInfo.getProduct().getPrice())
                .average()
                .orElse(0);
        return averagePrice;
    }

    @Override
    public double calcSpentMoney(LocalDate from, LocalDate to, Customer customer) {
        double spentMoney = customer.getMyChecks().stream()
                .filter(check -> check.getDate().isAfter(from) || check.getDate().equals(from) &&
                        check.getDate().isBefore(to) || check.getDate().equals(to))
                .mapToDouble((check -> check.getTotalSum()))
                .sum();
        return spentMoney;
    }


    @Override
    public void findTheLargestIncomeOfTheDay(Storehouse storehouse, Customer customer, LocalDate date) throws CustomException {
        Check checkLargestIncome = customer.getMyChecks().stream()
                .filter(check -> check.getDate().equals(date))
                .max((check1, check2) -> (int) (check1.getTotalSum() - check2.getTotalSum())) // Порівнюємо суми чеків
                .orElseThrow(() -> new CustomException("No checks for this date."));
        System.out.println("The largest income of the day: " + checkLargestIncome);
    }


    public ProductInfo findTheMostPopularProduct(Storehouse storehouse) {
        return storehouse.getAllCustomers().stream()
                .flatMap(customer -> customer.getMyChecks().stream())
                .flatMap(check -> check.getBoughtProducts().stream())
                .collect(Collectors.groupingBy(ProductInfo::getProduct, Collectors.summingInt(ProductInfo::getQuantity)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> new ProductInfo(entry.getKey(), entry.getValue()))
                .orElse(null);
    }

    @Override
    public Map<Product, Integer> getTotalQuantityOfEachProduct(Customer customer) {
        return customer.getMyChecks().stream()
                .flatMap(check -> check.getBoughtProducts().stream())
                .collect(Collectors.groupingBy(ProductInfo::getProduct, Collectors.summingInt(ProductInfo::getQuantity)));
    }

}


