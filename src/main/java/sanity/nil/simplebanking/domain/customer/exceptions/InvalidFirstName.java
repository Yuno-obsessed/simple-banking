package sanity.nil.simplebanking.domain.customer.exceptions;

public class InvalidFirstName extends RuntimeException{

    public InvalidFirstName(String message) {
        super(message);
    }

    public static InvalidFirstName invalidLength() {
        String message = "Invalid length less than 2 for firstName";
        return new InvalidFirstName(message);
    }

    public static InvalidFirstName invalidCharactersOccurred(String invalidChar) {
        String message = "One of such invalid chars occurred '" + invalidChar + "' occurred.";
        return new InvalidFirstName(message);
    }
}
