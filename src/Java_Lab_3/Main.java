package Java_Lab_3;

public class Main {
    public static void main(String[] args) {

        //FIXME: ДОДАТИ КОРИСТУВАЧА - ПОКУПЦЯ ААААААААААААА
        // TODO: розписати логіку наступних завдань по лабораторній

        // Початок роботи: отримуємо продукти зі складу:

        FileService productsService = new FileService();
        Storehouse store = new Storehouse();
        productsService.receiveOrder(); // цей метод повинен заповнити поле в магазині, тобто зчитати
        // в файлі продукцію та передати магазину.
        // store.printProduction(); -> перевірочний метод для виведення всієї продукції,
        // чи отримали ми продукцію з файлу.
        //FIXME: подумати, чи будемо в подальшому отримувати з складу всю продукцію, чи тільки необхідну

        store.open(12, 11, 2023) // - відкриваємо магазин та вказуємо дату, коли його відкриваємо.
        //цей метод(вище) повинен створити нам список покупок історії користувачів за день
        // наприкінці дня потрібно закрити магазин, тобто уНЕможливити виклик методу
        // open з такими параметрами, як вчора, скажімо.

        store.sellProducts(["milk", 2], ["bread", 4], ["bag", 1]); // цей метод буде: генерувати чек, видаляти з магазину ту кількість продуктів,
        // що ми передаємо, далі:
        // викликати метод, що додає згенерований нами чек до списку всіх чеків
        //TODO: створити папку з чеками!.
        // викликати метод, який уНЕможливлює перезапис цього файлу.
        // метод, що генерує чек, буде додавати перевірку на овочі чи фрукти та додавати для
        // кожного айтемса (овоч, фрукт) - пакет, що буде входити в загальну вартість товару.
        // bag - це загальний пакет до покупки. Можемо передавати, можемо ні.

        store.printCheck() // - буде прінтити останній чек в списку всіх чеків.

        store.sellProducts(["potato", 1], ["tomato", 2]);
        store.sellProducts(["avocado", 3], ["tomato", 1], ["fish", 3], ["bag", 2]);
        store.sellProducts(["potato", 3]);

        store.editProduct(["potato", 13.99]); // можемо передати різну кількість аргументів, та поредагувати
        // їх локально, тобто в себе в магазині.
        store.close(); // уНЕможливлює відкриття магазину (створення списку покупок) з такими параметрами,
        // Що вже містяться в списку.

        store.printHistory(); // - видрукуємо список покупок
        // TODO: подумати, чи друкувати за день, чи друкувати ВСЮ історію

        productsService.createOder(); // - в цей метод повинен передаватись список того,
        // що нам потрібно. Цей метод перезаписує файловий текст так, щоб кількість
        // продукції збільшилась. TODO: подумати над тим, як будемо ДОПЛЮСОВУВАТИ товари, яких потребуємо
        // можливо, зчитаємо кількість продукції з файлу, додамо до неї, ту, яку потребуємо та
        // та перезапишемо у файл. Тоді зможемо викликати метод recieve, який
        // буде отримувати нову кількість товару.
        // в подальшому будемо використовувати рідко, хіба що коли кількість продукції
        // яка буде на складі не буде відповідати нашому запиту.

        productsService.receiveOrder(); // TODO: подумати, чи будемо ручками це прописувати чи
        // чи це в нас буде викликати верхній метод!!

        store.open(13, 11, 2023);
        store.sellProducts(["carrot", 5]);
        store.sellProducts(["apple", 10]);
        store.sellProducts(["egg", 120]);
        store.sellProducts(["milk", 300]);

        store.close();

        store.open(14, 11, 2023);
        store.sellProducts(["potato", 3]);
        store.sellProducts(["egg", 120]); // тут має спрацьовувати перевірка, чи є такий продукт на складі. Якщо такого продукту немає,
        // слід надрукувати повідомелння, про те, що не можемо продати, оскільки
        // продукція закінчилась. Якщо перевірка не спрацювала, мусимо передати як параметр продукт,
        // що його хочемо поновити в магазині TODO: подумати, чи метод отримання продукції буде отримувати продукцію
        //  по товарах чи всю одразу, що є на складі.

        // також слід додати перевірку до генерації чеку, чи містить покупка м'ясо або рибу...
        // for ( String fish : fishList) {
        //      if (fish == item) {
        //      // addCommentToCheck(fish); -> додаємо до чеку коментар про товар

        // for ( String meat : meatList ) {
        //      if (meat == item) {
        //      // addCommentToCheck(meat); ->

        //FIXME: зробити через СТРІМИИИИ!!!!!!!!!!!!!!!!!!!!!!!

        productsService.filterByPrice(); // метод, що фільтрує товари на складі за ціною

        productsService.defineAveragePrice(); // метод, що визначає середнє значення по ціні серед усіх продуктівю




    }
}
