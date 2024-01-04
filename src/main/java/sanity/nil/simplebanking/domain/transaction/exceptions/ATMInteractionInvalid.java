package sanity.nil.simplebanking.domain.transaction.exceptions;

public class ATMInteractionInvalid extends RuntimeException{

    public ATMInteractionInvalid(String message) {
        super(message);
    }

    public static ATMInteractionInvalid atmNotEnoughBalance() {
        String message = "ATM has no enough balance to satisfy current transaction.";
        return new ATMInteractionInvalid(message);
    }

}
