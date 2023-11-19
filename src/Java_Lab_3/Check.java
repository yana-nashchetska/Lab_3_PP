package Java_Lab_3;

import javax.print.attribute.standard.DocumentName;
import java.beans.Customizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Check {
    private LocalDate date;

    private String storageName;
    private ArrayList<ProductInfo> boughtProducts;
    private String customerName;
    private String customerSurname;
    private ArrayList<String> comments;
    private ArrayList<String> bags = new ArrayList<>();
    private double totalSum;
    private boolean isPaid = false;

    public Check(LocalDate date,
                 String storageName,
                 String customerName,
                 String customerSurname,
                 boolean isPaid,
                 ProductInfo... args) {
        this.date = date;
        this.storageName = storageName;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.boughtProducts = new ArrayList<>(Arrays.asList(args));
        this.comments = new ArrayList<>();
        this.bags = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public ArrayList<ProductInfo> getBoughtProducts() {
        return boughtProducts;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("   -----  THIS IS CHECK:  -----  \n");
        result.append("=============================\n");
        result.append("date: ").append(date).append("\n");
        result.append(" You,").append(customerName).append(" ").append(customerSurname).append("\t\n");
        result.append("bought products in ").append(storageName).append("\t\n");
        result.append("Products: \n").append(boughtProducts).append("\t\n");

        for (ProductInfo productInfo : boughtProducts) {
            String productName = productInfo.getProduct().getName();
            if (isVegetableOrFruit(productName)) {
                result.append("Bag: ").append(productName).append("\n");
            } else if (isMeatOrFish(productName)) {
                result.append("Comment: Don't forget to store ").append(productName).append(" in the fridge.\n");
            }
        }

        result.append("=============================");
        result.append("TOTAL SUM: ").append(totalSum);
        result.append("=============================");

        return result.toString();
    }

    private boolean isVegetableOrFruit(String productName) {
        return Arrays.asList("apple", "banana", "orange", "lemon", "blueberry", "pear").contains(productName);
    }

    private boolean isMeatOrFish(String productName) {
        return Arrays.asList("fish", "meat").contains(productName);
    }

    public double getTotalSum() {
        return totalSum;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
