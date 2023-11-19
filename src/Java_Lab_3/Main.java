package Java_Lab_3;

import java.time.LocalDateTime;
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

        storehouseService.buy(storehouse, ivanPetrenko,
                LocalDateTime.of(2023, 11, 18, 8, 0),
                new ProductInfo(new Product("bread", storehouse), 10));
        ivanPetrenko.addCheck(storehouseService.getLastCheck(ivanPetrenko));

        System.out.println("=========================");
        storehouseService.buy(storehouse, ivanPetrenko,
                LocalDateTime.of(2023, 11, 18, 9, 0),
                new ProductInfo(new Product("fish", storehouse), 100));
        ivanPetrenko.addCheck(storehouseService.getLastCheck(ivanPetrenko));

        System.out.println("=========================");
        fileServiceImpl.printAllProducts(storehouse);// для перевірки

        System.out.println("=========================");
        //storehouse.getAllCustomers().forEach(System.out::println);


        try {
            storehouseService.printAllChecks(storehouse);
        } catch (CustomException e) {
            System.err.println("CustomException caught: " + e.getMessage());
            e.printStackTrace(); // друкуємо стек викликів, це допоможе нам знайти помилку
        }

        storehouseService.editStorehouse(storehouse, new Product("bread", storehouse), 15); // новий прайс бреда
        storehouseService.buy(storehouse, petroIvanenko, LocalDateTime.of(2023,11,19,14,36),
                new ProductInfo(new Product("bread", storehouse), 10),
                new ProductInfo(new Product("fish", storehouse), 100),
                new ProductInfo(new Product("milk", storehouse), 50));


        fileServiceImpl.orderProducts(storehouse, warehousePath,
                new ProductInfo(new Product("bread", storehouse), 1000),
                new ProductInfo(new Product("fish", storehouse), 1000),
                new ProductInfo(new Product("milk", storehouse), 5000)); // ми замовили 1000 хліба, 1000 риби та 5000 молока
        // і відразу ж отримали їх у магазин

        fileServiceImpl.printAllProducts(storehouse);// для перевірки

        storehouseService.buy(storehouse, ivanPetrenko, LocalDateTime.of(2023,11,20,14,36),
                new ProductInfo(new Product("bread", storehouse), 1));
        fileServiceImpl.orderProducts(storehouse, warehousePath,
                new ProductInfo(new Product("bread", storehouse), 90_000));

        fileServiceImpl.printAllProducts(storehouse);// для перевірки
    }
}

