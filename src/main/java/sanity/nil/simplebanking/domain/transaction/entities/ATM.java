package sanity.nil.simplebanking.domain.transaction.entities;

import sanity.nil.simplebanking.domain.transaction.vo.Address;
import sanity.nil.simplebanking.domain.transaction.vo.TransactionRestriction;

import java.math.BigDecimal;

public class ATM {

    private Integer atmID;
    private Bank bank;
    private Address address;
    private BigDecimal balanceLeft;
    private TransactionRestriction transactionRestriction;
    private BigDecimal differentBankFee;

    public ATM(Integer atmID, Bank bank, Address address, BigDecimal balanceLeft,
               TransactionRestriction transactionRestriction, BigDecimal differentBankFee) {
        this.atmID = atmID;
        this.bank = bank;
        this.address = address;
        this.balanceLeft = balanceLeft;
        this.transactionRestriction = transactionRestriction;
        this.differentBankFee = differentBankFee;
    }

    public Bank getBank() {
        return bank;
    }

    public BigDecimal getBalanceLeft() {
        return balanceLeft;
    }

    public TransactionRestriction getTransactionRestriction() {
        return transactionRestriction;
    }

    public BigDecimal getDifferentBankFee() {
        return differentBankFee;
    }
}
