package sanity.nil.simplebanking.domain.account.vo;

import java.math.BigDecimal;

public class Currency {

    private String currency;
    private BigDecimal exchangeRate;

    public Currency(String currency, BigDecimal exchangeRate) {
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }
}
