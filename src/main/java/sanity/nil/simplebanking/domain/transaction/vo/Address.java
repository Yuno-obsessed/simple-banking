package sanity.nil.simplebanking.domain.transaction.vo;

public class Address {

    private String country;
    private String city;
    private String street;
    private Integer buildingNumber;

    public Address(String country, String city, String street, Integer buildingNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }
}
