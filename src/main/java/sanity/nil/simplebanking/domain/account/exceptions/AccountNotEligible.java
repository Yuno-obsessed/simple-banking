package sanity.nil.simplebanking.domain.account.exceptions;

import sanity.nil.simplebanking.domain.account.consts.TransactionType;

public class AccountNotEligible extends RuntimeException {

    public AccountNotEligible(String message) {
        super(message);
    }

    public static AccountNotEligible notEligibleType(TransactionType transactionType) {
       String message = "Account's type is not eligible for transaction with type = " + transactionType.name();
       return new AccountNotEligible(message);
    }
}
