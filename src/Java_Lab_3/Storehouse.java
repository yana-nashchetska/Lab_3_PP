package Java_Lab_3;

import java.util.*;

public class Storehouse {
    private final String name = " StoreHOUSE ";

  /*  private final String[] fish = {"Salmon", "Tuna", "Cod", "Trout", "Sardine"}; // щоб перевірити чи продукт належить до певної категорії, достатньо порівняти його з елементами масиву
    private final String[] meat = {"Pork", "Beef", "Chicken", "Turkey", "Duck"};
    private final String[] vegetables = {"Potato", "Tomato", "Cucumber", "Carrot", "Onion"};
    private final String[] fruits = {"Apple", "Banana", "Orange", "Lemon", "Pineapple"};*/

    private ArrayList<ProductInfo> allProducts = new ArrayList<>(); // містить всі продукти, які є в магазині

    private ArrayList<Customer> allCustomers = new ArrayList<>();

    public Storehouse(ArrayList<ProductInfo> allProducts, ArrayList<Customer> allCustomers) {
        this.allProducts = allProducts;
        this.allCustomers = allCustomers;
    }

    public ArrayList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public void setAllCustomers(ArrayList<Customer> allCustomers) {
        this.allCustomers = allCustomers;
    }

    public void setAllProducts(ArrayList<ProductInfo> allProducts) {
        this.allProducts = allProducts;
    }

    public Storehouse() {
    }

    public ArrayList<ProductInfo> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(String name, double price, int quantity)
    {
            this.allProducts.add(new ProductInfo(new Product(name, price), quantity));
    }

/*
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
*/


    @Override
    public String toString() {
        return "Storehouse" + this.name +
                "\nall products: " + allProducts;
    }


    public String getName() {
        return this.name;
    }
}

