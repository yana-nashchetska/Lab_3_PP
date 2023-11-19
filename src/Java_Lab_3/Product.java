package Java_Lab_3;

import java.util.Objects;

public class Product
{
    private String name;

    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Product (String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, Storehouse storehouse) {
        this.name = name;
        ProductInfo productInfo = storehouse.getAllProducts()
                .stream()
                .filter(info -> info.getProduct().getName().equals(name))
                .findFirst()
                .orElse(null);

        if (productInfo != null) {
            this.price = productInfo.getProduct().getPrice();
        } else {
            System.out.println(" we haven't got this product sorry");
        }
    }



    @Override
    public String toString() {
        return name + "\t---- " + price + " UAH  ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}