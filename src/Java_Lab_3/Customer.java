package Java_Lab_3;

import java.util.ArrayList;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final ArrayList<Check> myChecks = new ArrayList<>();

    public Customer(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addCheck(Check check) {
        myChecks.add(check);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<Check> getMyChecks() {
        return myChecks;
    }



    @Override
    public String toString() {
        StringBuilder checksString = new StringBuilder();
        for (Check check : myChecks) {
            checksString.append("\n").append(check);
        }

        return "Customer: " + firstName + " " + lastName +
                "\nMy checks:" + checksString;
    }
}
