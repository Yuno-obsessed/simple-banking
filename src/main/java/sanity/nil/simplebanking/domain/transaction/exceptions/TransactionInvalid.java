package sanity.nil.simplebanking.domain.transaction.exceptions;

public class TransactionInvalid extends RuntimeException {

    public TransactionInvalid(String message) {
        super(message);
    }

    public static TransactionInvalid noParticipants() {
        String message = "Transaction has no participants.";
        return new TransactionInvalid(message);
    }

    public static TransactionInvalid amountIsEmpty() {
        String message = "Transaction's amount is empty.";
        return new TransactionInvalid(message);
    }

    public static TransactionInvalid isRejected() {
        String message = "Transaction was rejected.";
        return new TransactionInvalid(message);
    }

    public static TransactionInvalid noCompletionTime() {
        String message = "Transaction completion time wasn't registered.";
        return new TransactionInvalid(message);
    }
}
