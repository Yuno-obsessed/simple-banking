package sanity.nil.simplebanking.domain.transaction.entities;

import sanity.nil.simplebanking.domain.account.vo.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Balance {

    private BigDecimal balance;
    private Currency currency;

    public Balance(BigDecimal balance, Currency currency) {
        this.balance = balance;
        this.currency = currency;
    }

    public BigDecimal balanceInAnotherCurrency(Currency currency) {
        if (currency.equals(this.currency)) {
            return balance;
        }

        return balance.multiply(currency.getExchangeRate()).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
