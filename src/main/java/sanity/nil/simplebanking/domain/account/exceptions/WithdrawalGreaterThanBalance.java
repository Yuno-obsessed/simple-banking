package sanity.nil.simplebanking.domain.account.exceptions;

import java.math.BigDecimal;

public class WithdrawalGreaterThanBalance extends RuntimeException{

    public WithdrawalGreaterThanBalance(BigDecimal withdrawal, BigDecimal balance) {
        super(String.format("Withdrawal = %s is greater than balance = %s.", withdrawal, balance));
    }
}
