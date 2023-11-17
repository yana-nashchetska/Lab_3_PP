package Java_Lab_3;

public interface FileService {
    void receiveAllProducts();
    void orderProducts(Order... args); // буде збільшувати кількість продуктів на складі, на ту кількість, якої нам не вистачило.  Тобто перезаписуватиме
    // кількість продуктів у файлі складу.

    void receiveProducts(Order... args); // отримуємо певні продукти зі складу, тобто викликаємо метод, що буде поновлювати кількість продуктів в магазині
// склад буде окремо від магазину.

    void sortByPrice(); // сортуємо продукти по ціні
}
