package Java_Lab_3;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Main {


    public static void main(String[] args) {

        String warehousePath = "D:\\PP\\Labs\\Java_Lab_3\\warehouse.txt";

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

       storehouseService.buy(storehouse,ivanPetrenko,
               LocalDateTime.of(2023, 11, 18, 8, 0),
               new ProductInfo(new Product("bread", storehouse), 10));

        System.out.println("=========================");
        fileServiceImpl.printAllProducts(storehouse);// для перевірки

        System.out.println("=========================");
    }
}
