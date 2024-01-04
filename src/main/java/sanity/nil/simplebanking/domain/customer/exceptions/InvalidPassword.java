package sanity.nil.simplebanking.domain.customer.exceptions;

public class InvalidPassword extends RuntimeException{

    public InvalidPassword(String message) {
        super(message);
    }

    public static InvalidPassword invalidLength() {
        String message = "Invalid length less than 2 for firstName";
        return new InvalidPassword(message);
    }

    public static InvalidPassword noSpecialChars() {
        String message = "No special required char as '!','.','_' in password.";
        return new InvalidPassword(message);
    }

    public static InvalidPassword noDigit() {
        return new InvalidPassword("No digit in password.");
    }

    public static InvalidPassword noUpperCase() {
        return new InvalidPassword("No upper case character in password.");
    }
}
