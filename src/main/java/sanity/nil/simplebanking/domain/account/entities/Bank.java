package sanity.nil.simplebanking.domain.account.entities;

public class Bank {

    private Integer bankID;
    private String country;

    public Bank(Integer bankID, String country) {
        this.bankID = bankID;
        this.country = country;
    }

    public Integer getBankID() {
        return bankID;
    }

    public String getCountry() {
        return country;
    }
}
