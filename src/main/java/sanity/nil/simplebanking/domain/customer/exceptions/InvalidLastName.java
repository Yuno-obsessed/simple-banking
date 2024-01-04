package sanity.nil.simplebanking.domain.customer.exceptions;

public class InvalidLastName extends RuntimeException{

    public InvalidLastName(String message) {
        super(message);
    }

    public static InvalidLastName invalidLength() {
        String message = "Invalid length less than 2 for firstName";
        return new InvalidLastName(message);
    }

    public static InvalidLastName invalidCharactersOccurred(String invalidChar) {
        String message = "One of such invalid chars occurred '" + invalidChar + "' occurred.";
        return new InvalidLastName(message);
    }
}
