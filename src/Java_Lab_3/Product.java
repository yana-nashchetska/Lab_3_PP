package Java_Lab_3;
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

    public Product(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name + " - " + price + " UAH";
    }
}