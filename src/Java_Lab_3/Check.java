package Java_Lab_3;

import java.beans.Customizer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Check {
    private LocalDateTime date;

    private String storageName;
    private ArrayList<ProductInfo> boughtProducts;
    private String customerName;
    private String customerSurname;
    private double totalSum;


    public Check(LocalDateTime date,
                 String storageName,
                 String customerName,
                 String customerSurname,
                 ProductInfo... args) {
        this.date = date;
        this.storageName = storageName;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.boughtProducts = new ArrayList<>(Arrays.asList(args));
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public ArrayList<ProductInfo> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(ArrayList<ProductInfo> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    @Override
    public String toString() {
        return "   -----  THIS IS CHECK:  -----  \n" +
                "=============================" +
                "date: " + date +
                "\n You," + customerName + customerSurname + "\t\n" +
                "bought products in " + storageName + "\t\n" +
                "Products: \n" + boughtProducts + "\t\n" +
                "=============================" +
                "TOTAL SUM: " + totalSum +
                "=============================";
    }
}
