package sanity.nil.simplebanking.domain.customer.exceptions;

public class InvalidPhoneNumber extends RuntimeException{

    public InvalidPhoneNumber(String message) {
        super(message);
    }

    public static InvalidPhoneNumber invalidFormat() {
        String message = "Invalid format containing not only numbers for phone number.";
        return new InvalidPhoneNumber(message);
    }
}
