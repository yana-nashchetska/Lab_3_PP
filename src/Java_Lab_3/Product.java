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
        return name + "---- " + price + " UAH";
    }

}