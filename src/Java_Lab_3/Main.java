package Java_Lab_3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.util.HashMap;

public class Main {


    public static void main(String[] args) {

        String warehousePath = "D:\\PP\\Labs\\Java_Lab_3\\warehouse.txt";
        String checksPath = "D:\\PP\\Labs\\Java_Lab_3\\Checks_java_3";

        Storehouse storehouse = new Storehouse();

        StorehouseImpl storehouseService = new StorehouseImpl();
        FileServiceImpl fileServiceImpl = new FileServiceImpl();

        fileServiceImpl.receiveAllProducts(storehouse, warehousePath);
        fileServiceImpl.printAllProducts(storehouse);// для перевірки

        Customer ivanPetrenko = new Customer("Ivan", "Petrenko");
        Customer petroIvanenko = new Customer("Petro", "Ivanenko");

        storehouseService.addCustomer(storehouse, ivanPetrenko);
        storehouseService.addCustomer(storehouse, petroIvanenko);

        System.out.println("=========================");

        storehouseService.buy(storehouse, ivanPetrenko, LocalDate.of(2023, 11, 18), new ProductInfo(new Product("bread", storehouse), 10));


        System.out.println("=========================");
        storehouseService.buy(storehouse, ivanPetrenko, LocalDate.of(2023, 11, 18), new ProductInfo(new Product("fish", storehouse), 100));


        System.out.println("=========================");
        fileServiceImpl.printAllProducts(storehouse);// для перевірки

        System.out.println("=========================");

        storehouseService.editStorehouse(storehouse, new Product("bread", storehouse), 15); // новий прайс бреда


        storehouseService.buy(storehouse, petroIvanenko, LocalDate.of(2023, 11, 19), new ProductInfo(new Product("bread", storehouse), 10), new ProductInfo(new Product("fish", storehouse), 100), new ProductInfo(new Product("milk", storehouse), 50));

        fileServiceImpl.orderProducts(storehouse, warehousePath, new ProductInfo(new Product("bread", storehouse), 1000), new ProductInfo(new Product("fish", storehouse), 1000), new ProductInfo(new Product("milk", storehouse), 5000)); // ми замовили 1000 хліба, 1000 риби та 5000 молока
        // і відразу ж отримали їх у магазин

        fileServiceImpl.printAllProducts(storehouse);// для перевірки

        storehouseService.buy(storehouse, ivanPetrenko, LocalDate.of(2023, 11, 20), new ProductInfo(new Product("bread", storehouse), 1));

        fileServiceImpl.orderProducts(storehouse, warehousePath, new ProductInfo(new Product("bread", storehouse), 90_000));

        fileServiceImpl.printAllProducts(storehouse);// для перевірки
        System.out.println("=========================");
        System.out.println("Average price: " + storehouseService.calcAveragePrice(storehouse));
        System.out.println("=========================");
        System.out.println("Total sum:" + ivanPetrenko.getFirstName() + "  " + ivanPetrenko.getLastName() + "  of certain period of time: (from 10/11/2023 to 20/11/2023)\n ");
        System.out.println(storehouseService.calcSpentMoney(LocalDate.of(2023, 11, 10), LocalDate.of(2023, 11, 20), ivanPetrenko));

        System.out.println("=========================");
        storehouse.getAllCustomers().forEach(System.out::println);  // для перевірки- ЗБЕРЕЖЕННЯ ІСТОРІЇ ПОКУПОК НАЧЕ
        System.out.println("=========================");
        System.out.println("The largest income of the day: 18/11/2023");

        try {
            // Викликаємо метод
            storehouseService.findTheLargestIncomeOfTheDay(storehouse, ivanPetrenko, LocalDate.of(2023, 11, 18));
        } catch (CustomException e) {
            e.printStackTrace();
        }


        System.out.println("=========================");

        System.out.println("=========================");

        System.out.println("=========================");

        try {
            System.out.println("All checks: ");
            storehouseService.printAllChecks(storehouse);
        } catch (CustomException e) {
            System.err.println("CustomException caught: " + e.getMessage());
            e.printStackTrace(); // друкуємо стек викликів, це допоможе нам знайти помилку
        }

        System.out.println("=========================");
        fileServiceImpl.sortByPrice(warehousePath); // сортуємо продукти по ціні
        System.out.println("products in warehouse were sorted!");

        System.out.println("=========================");
       // System.out.println("All customers: \n");
        //storehouse.getAllCustomers().forEach(System.out::println);
        System.out.println("The most popular product: ");
        System.out.println(storehouseService.findTheMostPopularProduct(storehouse));
        System.out.println("=========================");

        storehouseService.buy(storehouse, ivanPetrenko, LocalDate.of(2023, 11, 21),
        new ProductInfo(new Product("meat", storehouse), 890));
        storehouseService.buy(storehouse, ivanPetrenko, LocalDate.of(2023, 11, 21),
                new ProductInfo(new Product("meat", storehouse), 890));
        System.out.println("Statisctics about Ivan: ");
        System.out.println(storehouseService.getTotalQuantityOfEachProduct(ivanPetrenko));

    }
}

