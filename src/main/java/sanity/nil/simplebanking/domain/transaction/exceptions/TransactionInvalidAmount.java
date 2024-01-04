package sanity.nil.simplebanking.domain.transaction.exceptions;

public class TransactionInvalidAmount extends RuntimeException{

    public TransactionInvalidAmount(String message) {
        super(message);
    }

    public static TransactionInvalidAmount invalidAmountCurrency() {
        String message = "Transaction's amount currency isn't the same as sender's balance currency.";
        return new TransactionInvalidAmount(message);
    }

}
