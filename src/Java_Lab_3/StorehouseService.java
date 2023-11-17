package Java_Lab_3;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface StorehouseService {
    void sellProducts(String name, String surname, Order... args); //
    // буде продавати продукти певному користувачу. Якщо такий користувач вже є, то буде додавати цей ордер до
    // списку його продуктів. Якщо немає, то буде створювати нового користувача і додавати йому цей ордер.
    // користувачі містяться в загалному списку користувачів.

    void editStorehouse(Order order, int productPrice); // в магазині будемо редагувати ціну
    // продукту. Не редагуємо на складі, бо магазин може виставляти свою ціну окремо від складу, та робити націнку.

    void printCheck(); // прінтимо певний чек у txt форматі.

    void addBag(); // дозапише до чеку пакет та його ціну.
    void addComment(); // для додавання коментаря до чеку

    void averagePrice(); // середня ціна продуктів в магазині.

    void spentMoney(LocalDateTime from, LocalDateTime to, String name, String surname); // скільки грошей витратив певний користувач за певний період часу.

    HashMap<String, Integer> getProductsStatistics(String name, String surname); // статистика покупок певного користувача.
    // виводимо назву продукту та кількість, яку він купив.

    //TODO: вивести найпопулярніший продукт в магазині.
    // можемо викликати для кожного користувача метод зі статистикою, тоді знайти в кожного користувача найпопулярніщий продукт
    // далі порівняти найпопулярніший продукт між всіма користувачами
    // а тоді вивести найпопулярніший продукт.
    // якщо найпопулярніший продукт буде півпадати в кількох користувачів, просумувати частоту купляння цього продукту та вивести його.
    // якщо частота купляння однакова для певних продуктів, то вивести їх разом.


    //TODO: написати метод, якому передамо дату і за яким в списку користувачів будемо шукати найприбутковіший чек за цією датою.
    // Тоді вивести його.
}