package Java_Lab_3;

public interface FileService {
    void receiveAllProducts(Storehouse storehouse, String path);
    void orderProducts(ProductInfo... args); // буде збільшувати кількість продуктів на складі, на ту кількість, якої нам не вистачило.  Тобто перезаписуватиме
    // кількість продуктів у файлі складу.

    void printAllProducts(Storehouse storehouse);

    void receiveProducts(Storehouse storehouse, ProductInfo... args); // отримуємо певні продукти зі складу, тобто викликаємо метод, що буде поновлювати кількість продуктів в магазині
// склад буде окремо від магазину.

    void sortByPrice(); // сортуємо продукти по ціні
}
