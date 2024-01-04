package sanity.nil.simplebanking.domain.customer.exceptions;

public class InvalidEmail extends RuntimeException{

    public InvalidEmail(String message) {
        super(message);
    }

    public static InvalidEmail invalidLength() {
        String message = "Invalid length less than 8 for email";
        return new InvalidEmail(message);
    }

    public static InvalidEmail invalidEmailFormat() {
        return new InvalidEmail("Email specified is not a valid email.");
    }
}
