package Java_Lab_3;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Check {
    private LocalDateTime dateOfPurchase;

    private ArrayList<Order> orders;
    private double totalSum;

    public Check(LocalDateTime dateOfPurchase, Customer customer, ArrayList<Order> orders) {
        this.dateOfPurchase = dateOfPurchase;
        this.orders = orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
    public LocalDateTime getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    @Override
    public String toString() {
        return "===================== \n" +
                "date: " + dateOfPurchase +
                "\n orders: \n" + orders +
                "=====================" +
                "\n TOTAL SUM = " + totalSum +
                "=====================";
    }
}
