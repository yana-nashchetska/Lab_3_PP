package Java_Lab_3;

public class ProductInfo {
    private Product product;

    private int quantity;

    public ProductInfo(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductInfo() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return product + " \t" + quantity + " pcs\t\n";
    }
}
