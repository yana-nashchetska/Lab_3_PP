package Java_Lab_3;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class StorehouseImpl implements StorehouseService {
    public Check getLastCheck(Customer customer) {
        return customer.getMyChecks().isEmpty() ? null : customer.getMyChecks().get(customer.getMyChecks().size() - 1);
    }

    public void buy(Storehouse storehouse, Customer customer, LocalDate date, ProductInfo... args) {
        customer.addCheck(new Check(date,
                storehouse.getName(),
                customer.getFirstName(), customer.getLastName(), true,
                args));

        calcTotalSum(customer);
        recalculateQuantity(storehouse, customer, args);
        printCheck(customer);
        setStatus(customer, true);
    }

    public void addItem(Storehouse storehouse, Customer customer, Product product, int quantity) {
        storehouse.getAllCustomers().stream()
                .filter(c -> c.equals(customer))
                .findFirst()
                .ifPresent(c -> {
                    ArrayList<Check> checks = c.getMyChecks();
                    if (!checks.isEmpty()) {
                        Check lastCheck = checks.get(checks.size() - 1);
                        try {
                            if (!lastCheck.isPaid()) {
                                ProductInfo productInfo = new ProductInfo();
                                productInfo.setProduct(product);
                                productInfo.setQuantity(quantity);
                                lastCheck.getBoughtProducts().add(productInfo);
                            } else {
                                throw new IllegalStateException("Cannot add items to a paid check");
                            }
                        } catch (IllegalStateException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
    }


    @Override
    public void calcTotalSum(Customer customer) {

        Check lastCheck = getLastCheck(customer);

        if (lastCheck != null) {

            ArrayList<ProductInfo> products = lastCheck.getBoughtProducts();

            double totalSum = products.stream()
                    .mapToDouble(productInfo -> productInfo.getProduct().getPrice() * productInfo.getQuantity())
                    .sum();
            lastCheck.setTotalSum(totalSum);
        } else {
            System.out.println("No checks to calculate total sum.");
        }

    }

    public void printCheck(Customer customer) {
        Check lastCheck = getLastCheck(customer);

        if (lastCheck != null) {
            String fileName = "check_" + customer.getFirstName() + "_" + customer.getLastName() + "_" + System.currentTimeMillis() + ".txt";
            Path checkPath = Paths.get("D:\\PP\\Labs\\Java_Lab_3\\Checks_java_3", fileName);
            try {
                Files.write(checkPath, lastCheck.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
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
            System.out.println(customer);

            if (customer.getMyChecks().isEmpty()) {
                System.out.println("No checks to print for this customer." + customer.getFirstName() + " " + customer.getLastName());
            }
        });
    }


    public void setStatus( Customer customer, boolean status) {
        customer.getMyChecks().stream()
                .filter(check -> check.equals(getLastCheck(customer)))
                .forEach(check -> check.setPaid(status));
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
    }


    @Override
    public void addCustomer(Storehouse storehouse, Customer customer) {
        storehouse.getAllCustomers().add(customer);
    }

    @Override
    public void editStorehouse(Storehouse storehouse, Product product, int productPrice) {
        storehouse.getAllProducts()
                .forEach(prInfStr -> {
                    if (prInfStr.getProduct().getName().equals(product.getName())) {
                        prInfStr.getProduct().setPrice(productPrice);
                    }
                });
    }

    @Override
    public double calcAveragePrice(Storehouse storehouse) {
        return storehouse.getAllProducts().stream()
                .mapToDouble(productInfo -> productInfo.getProduct().getPrice())
                .average()
                .orElse(0);

    }

    @Override
    public double calcSpentMoney(LocalDate from, LocalDate to, Customer customer) {
        return customer.getMyChecks().stream()
                .filter(check -> check.getDate().isAfter(from) || check.getDate().equals(from) &&
                        check.getDate().isBefore(to) || check.getDate().equals(to))
                .mapToDouble((Check::getTotalSum))
                .sum();
    }


    @Override
    public void findTheLargestIncomeOfTheDay(Storehouse storehouse, Customer customer, LocalDate date) throws CustomException {
        Check checkLargestIncome = customer.getMyChecks().stream()
                .filter(check -> check.getDate().equals(date))
                .max((check1, check2) -> (int) (check1.getTotalSum() - check2.getTotalSum())) // Порівнюємо суми чеків
                .orElseThrow(() -> new CustomException("No checks for this date."));
        System.out.println("The largest income of the day: " + checkLargestIncome);
    }


    @Override
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


