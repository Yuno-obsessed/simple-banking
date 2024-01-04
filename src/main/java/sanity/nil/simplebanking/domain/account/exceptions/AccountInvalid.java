package sanity.nil.simplebanking.domain.account.exceptions;

public class AccountInvalid extends RuntimeException{

    public AccountInvalid(String message) {
        super(message);
    }

    public static AccountInvalid isBlockedOrNotValid() {
        String message = "Account is blocked or not valid.";
        return new AccountInvalid(message);
    }

    public static AccountInvalid balanceInvalid() {
        String message = "Account's balance is lower than zero.";
        return new AccountInvalid(message);
    }

    public static AccountInvalid isRejected() {
        String message = "Account creation was rejected. Can't perform any action with it.";
        return new AccountInvalid(message);
    }

}
