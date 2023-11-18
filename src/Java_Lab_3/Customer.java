package Java_Lab_3;

import java.util.ArrayList;

public class Customer {
    private String firstName;
    private String lastName;
    private ArrayList<Check> myChecks = new ArrayList<>();

    public Customer(String firstName, String lastName, ArrayList<Check> myChecks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.myChecks = myChecks;
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Check> getMyChecks() {
        return myChecks;
    }

    public void setMyChecks(ArrayList<Check> myChecks) {
        this.myChecks = myChecks;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", myChecks=" + myChecks +
                '}';
    }
}
