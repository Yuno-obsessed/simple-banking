package sanity.nil.simplebanking.domain.transaction.vo;

import sanity.nil.simplebanking.domain.transaction.consts.Frequency;

import java.math.BigDecimal;

public class TransactionRestriction {

    private BigDecimal amount;
    private Frequency frequency;

    public TransactionRestriction(BigDecimal amount, Frequency frequency) {
        this.amount = amount;
        this.frequency = frequency;
    }
}
