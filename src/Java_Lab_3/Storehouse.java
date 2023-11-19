package Java_Lab_3;

import java.util.*;

public class Storehouse {
    private final String name = " StoreHOUSE ";

    private ArrayList<ProductInfo> allProducts = new ArrayList<>();

    private ArrayList<Customer> allCustomers = new ArrayList<>();

    public ArrayList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public void setAllProducts(ArrayList<ProductInfo> allProducts) {
        this.allProducts = allProducts;
    }

    public Storehouse() {
    }

    public ArrayList<ProductInfo> getAllProducts() {
        return allProducts;
    }

    @Override
    public String toString() {
        return "Storehouse" + this.name +
                "\nall products: " + allProducts;
    }


    public String getName() {
        return this.name;
    }

    public ProductInfo getProductInfo(String name) {
        return this.allProducts.stream()
                .filter(productInfo -> productInfo.getProduct().getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}

