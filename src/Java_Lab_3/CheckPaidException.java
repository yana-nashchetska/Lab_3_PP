package Java_Lab_3;

public class CheckPaidException extends IllegalStateException {
    public CheckPaidException(String customerName) {
        super("Cannot add items to a paid check for customer: " + customerName);
    }
}

