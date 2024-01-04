package sanity.nil.simplebanking.domain.transaction.aggregate;

import sanity.nil.simplebanking.domain.account.entities.Balance;
import sanity.nil.simplebanking.domain.common.AggregateRoot;
import sanity.nil.simplebanking.domain.transaction.consts.TransactionMethod;
import sanity.nil.simplebanking.domain.transaction.consts.TransactionStatus;
import sanity.nil.simplebanking.domain.transaction.consts.TransactionType;
import sanity.nil.simplebanking.domain.transaction.entities.ATM;
import sanity.nil.simplebanking.domain.transaction.entities.Account;
import sanity.nil.simplebanking.domain.transaction.events.TransactionCreatedEvent;
import sanity.nil.simplebanking.domain.transaction.events.TransactionProcessingCompleted;
import sanity.nil.simplebanking.domain.transaction.events.TransactionRejectedEvent;
import sanity.nil.simplebanking.domain.transaction.exceptions.ATMInteractionInvalid;
import sanity.nil.simplebanking.domain.transaction.exceptions.TransactionInvalid;
import sanity.nil.simplebanking.domain.transaction.exceptions.TransactionInvalidAmount;
import sanity.nil.simplebanking.domain.transaction.vo.TransactionID;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static sanity.nil.simplebanking.domain.transaction.consts.TransactionStatus.*;

public class Transaction extends AggregateRoot {

    private TransactionID transactionID;
    private Balance amount;
    private Account sender;
    private Account recipient;
    private ATM atm;
    private TransactionMethod transactionMethod;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private LocalDateTime issuedAt;
    private LocalDateTime completedAt;

    public Transaction start() {
        verifyTransaction();
        transactionStatus = ISSUED;

        if (transactionMethod.equals(TransactionMethod.ATM) &&
                transactionType.equals(TransactionType.WITHDRAWAL)) {
            startATMWithdrawal();
        }

        if (transactionMethod.equals(TransactionMethod.ATM) &&
                transactionType.equals(TransactionType.DEPOSIT)) {
            startATMDeposit();
        }

        if (transactionMethod.equals(TransactionMethod.ONLINE) &&
                transactionType.equals(TransactionType.TRANSFER)) {
            startOnlineTransfer();
        }

        completeTransaction();

        return this;
    }

    public Transaction create(UUID transactionID, Balance amount, Account sender, Account recipient,
                              ATM atm, String transactionType, String transactionMethod) {
        this.transactionID = new TransactionID(transactionID);
        this.amount = amount;
        this.sender = sender;
        this.recipient = recipient;
        this.atm = atm;
        this.transactionType = TransactionType.valueOf(transactionType);
        this.transactionMethod = TransactionMethod.valueOf(transactionMethod);
        this.transactionStatus = ISSUED;
        this.issuedAt = LocalDateTime.now();

        verifyTransaction();
        this.recordEvent(new TransactionCreatedEvent(this));

        return this;
    }

    public void startATMWithdrawal() {
        if (atm.getBalanceLeft().compareTo(amount.getBalance()) < 0) {
            transactionError(ATMInteractionInvalid.atmNotEnoughBalance());
        }

        // TODO: add check whether this account has exceeded limits that are set for this atm

        BigDecimal senderBalance = sender.getBalance().getBalance();

        BigDecimal withdrawnBalance = amount.getBalance();
        if (!amount.getCurrency().equals(recipient.getBalance().getCurrency())) {
            withdrawnBalance = amount.balanceInAnotherCurrency(recipient.getBalance().getCurrency());
        }

        // atm fee for different banks on withdrawal
        if (!sender.getBank().getBankID().equals(atm.getBank().getBankID())) {
            withdrawnBalance = withdrawnBalance
                    .add(withdrawnBalance
                            .multiply(atm.getDifferentBankFee()));
        }

        sender.getBalance().setBalance(senderBalance.subtract(withdrawnBalance));
    }

    public void startATMDeposit() {
        BigDecimal senderBalance = sender.getBalance().getBalance();

        BigDecimal depositedBalance = amount.getBalance();
        if (!amount.getCurrency().equals(sender.getBalance().getCurrency())) {
            depositedBalance = amount.balanceInAnotherCurrency(sender.getBalance().getCurrency());
        }

        // no fee for deposit

        sender.getBalance().setBalance(senderBalance.add(depositedBalance));
    }

    public void startOnlineTransfer() {
        if (!this.amount.getCurrency().equals(sender.getBalance().getCurrency())) {
            transactionError(TransactionInvalidAmount.invalidAmountCurrency());
        }

        BigDecimal senderBalance = sender.getBalance().getBalance();
        BigDecimal recipientBalance = recipient.getBalance().getBalance();

        BigDecimal newBalance = amount.getBalance();
        if (!amount.getCurrency().equals(recipient.getBalance().getCurrency())) {
            newBalance = amount.balanceInAnotherCurrency(recipient.getBalance().getCurrency());
        }

        sender.getBalance().setBalance(senderBalance.subtract(newBalance));
        recipient.getBalance().setBalance(recipientBalance.add(newBalance));
    }

    public void changeTransactionStatus(TransactionStatus transactionStatus) {
        verifyTransaction();
        this.transactionStatus = transactionStatus;
    }

    public void verifyTransaction() {
        RuntimeException exception = null;
        if (sender == null && recipient == null) {
            exception = TransactionInvalid.noParticipants();
        }
        if (amount == null || amount.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            exception = TransactionInvalid.amountIsEmpty();
        }
        if (transactionStatus.equals(REJECTED)) {
            exception = TransactionInvalid.isRejected();
        }
        if (transactionStatus.equals(COMPLETED) && completedAt == null) {
            exception = TransactionInvalid.noCompletionTime();
        }

        if (exception != null) {
           transactionError(exception);
        }
    }

    public void completeTransaction() {
        verifyTransaction();
        this.transactionStatus = COMPLETED;
        this.completedAt = LocalDateTime.now();
        this.recordEvent(new TransactionProcessingCompleted(this));
    }

    public boolean isCompleted() {
        verifyTransaction();

        return transactionStatus.equals(COMPLETED) && completedAt != null;
    }

    public void transactionError(RuntimeException exception) {
        this.transactionStatus = REJECTED;
        this.recordEvent(new TransactionRejectedEvent(this, exception.getMessage()));
        throw exception;
    }

    public TransactionID getTransactionID() {
        return transactionID;
    }

    public Account getSender() {
        return sender;
    }

    public Account getRecipient() {
        return recipient;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public Balance getAmount() {
        return amount;
    }
}
