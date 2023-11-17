package Java_Lab_3;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Storehouse {
    private final String name = " StoreHOUSE ";

    private final String[] fish = {"Salmon", "Tuna", "Cod", "Trout", "Sardine"}; // щоб перевірити чи продукт належить до певної категорії, достатньо порівняти його з елементами масиву
    private final String[] meat = {"Pork", "Beef", "Chicken", "Turkey", "Duck"};
    private final String[] vegetables = {"Potato", "Tomato", "Cucumber", "Carrot", "Onion"};
    private final String[] fruits = {"Apple", "Banana", "Orange", "Lemon", "Pineapple"};

    private HashMap<Product, Integer> allProducts = new HashMap<>(); // містить всі продукти, які є в магазині
    private ArrayList<Customer> customers = new ArrayList<>();

    public Storehouse() {
    }

    public HashMap<Product, Integer> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(HashMap<Product, Integer> allProducts) {
        this.allProducts = allProducts;
    }

    public String[] getFish() {
        return fish;
    }

    public String[] getMeat() {
        return meat;
    }

    public String[] getVegetables() {
        return vegetables;
    }

    public String[] getFruits() {
        return fruits;
    }

    /*@Override
    public void sellProducts(String name, String surname, Product... products) {
*/

    /*}*/

    @Override
    public String toString() {
        return "Storehouse" + this.name +
                "\nall products: " + allProducts;
    }
}

