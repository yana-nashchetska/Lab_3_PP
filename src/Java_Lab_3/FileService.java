package Java_Lab_3;

public interface FileService {
    void receiveAllProducts(Storehouse storehouse, String path);

    void printAllProducts(Storehouse storehouse);

    void orderProducts(Storehouse storehouse,String path, ProductInfo... args); // отримуємо певні продукти зі складу, тобто викликаємо метод, що буде поновлювати кількість продуктів в магазині
// склад буде окремо від магазину.

    void receiveProducts(Storehouse storehouse, String path, ProductInfo... args); // будемо зчитувати з редагованого файла всю кількість продукції яку замовляли зі складу


    void sortByPrice(); // сортуємо продукти по ціні


}
