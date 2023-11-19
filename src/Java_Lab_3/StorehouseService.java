package Java_Lab_3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface StorehouseService {

    void calcTotalSum(Customer customer); // рахуємо суму чеку

    void editStorehouse(Storehouse storehouse, Product product, int productPrice);

    void addCustomer(Storehouse storehouse, Customer customer);

    void recalculateQuantity(Storehouse storehouse, Customer customer, ProductInfo... args);

    void printCheck(Customer customer);

    void printAllChecks(Storehouse storehouse) throws Exception;

    double calcAveragePrice(Storehouse storehouse);

    double calcSpentMoney(LocalDate from, LocalDate to, Customer customer); // скільки грошей витратив певний користувач за певний період часу.

    void buy(Storehouse storehouse, Customer customer, LocalDate of, ProductInfo... args);

    void findTheLargestIncomeOfTheDay(Storehouse storehouse, Customer customer, LocalDate of) throws CustomException;


    ProductInfo findTheMostPopularProduct(Storehouse storehouse); // знаходимо найпопулярніший продукт

    Map<Product, Integer> getTotalQuantityOfEachProduct(Customer customer);

}

