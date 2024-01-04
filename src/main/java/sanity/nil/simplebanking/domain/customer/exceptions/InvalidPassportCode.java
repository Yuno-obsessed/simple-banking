package sanity.nil.simplebanking.domain.customer.exceptions;

public class InvalidPassportCode extends RuntimeException{

    public InvalidPassportCode(String message) {
        super(message);
    }

    public static InvalidPassportCode invalidLength() {
        String message = "Invalid length less than 6 for password code";
        return new InvalidPassportCode(message);
    }
}
