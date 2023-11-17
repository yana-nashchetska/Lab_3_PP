package Java_Lab_3;

import java.util.HashMap;

public class Main {


    public static void main(String[] args) {
        Storehouse storehouse = new Storehouse();
        StorehouseImpl storehouseImpl = new StorehouseImpl(storehouse);

        storehouseImpl.sellProducts("Olena", "Kovalenko", new Order(new Product("Milk"), 2),
                new Order(new Product("Bread"), 1),
                new Order(new Product("Cheese"), 1));
    }
}
