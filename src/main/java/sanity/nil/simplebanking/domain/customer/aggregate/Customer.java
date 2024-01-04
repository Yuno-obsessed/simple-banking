package sanity.nil.simplebanking.domain.customer.aggregate;

import sanity.nil.simplebanking.domain.common.AggregateRoot;
import sanity.nil.simplebanking.domain.customer.events.CustomerFailedVerifyEvent;
import sanity.nil.simplebanking.domain.customer.events.CustomerVerifiedEvent;
import sanity.nil.simplebanking.domain.customer.exceptions.*;
import sanity.nil.simplebanking.domain.customer.vo.CustomerID;
import sanity.nil.simplebanking.domain.customer.vo.PhoneNumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Customer extends AggregateRoot {

    private CustomerID customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private PhoneNumber phoneNumber;
    private String passportCode;
    private boolean emailVerified;

    public void verifyEmail() {
        validate();
        emailVerified = true;
    }

    public boolean validForAccountCreation() {
        if (!validate() && !emailVerified) {
            this.recordEvent(new CustomerFailedVerifyEvent(this));
            return false;
        }

        this.recordEvent(new CustomerVerifiedEvent(this));
        return true;
    }

    public boolean validate() {
        return validateEmail() &&
                validatePassword() &&
                validateFirstName() &&
                validatePassportCode() &&
                validatePhoneNumber() &&
                validateLastName();
    }

    public boolean validateFirstName() {
        if (firstName.length() < 2) {
            throw InvalidFirstName.invalidLength();
        }
        String invalidChars = validationCharacters(firstName);
        if (invalidChars != null) {
            throw InvalidFirstName.invalidCharactersOccurred(invalidChars);
        }
        return true;
    }

    public boolean validateLastName() {
        if (firstName.length() < 2) {
            throw InvalidLastName.invalidLength();
        }
        String invalidChars = validationCharacters(firstName);
        if (invalidChars != null) {
            throw InvalidLastName.invalidCharactersOccurred(invalidChars);
        }
        return true;
    }

    public boolean validateEmail() {
        if (email.length() < 8) {
            throw InvalidEmail.invalidLength();
        }
        Matcher emailMatcher = Pattern.compile("^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$").matcher(email);
        if (!emailMatcher.find()) {
            throw InvalidEmail.invalidEmailFormat();
        }
        return true;
    }

    public boolean validatePassword() {
        if (password.length() < 4) {
            throw InvalidPassword.invalidLength();
        }
        if (!password.contains("!") || !password.contains(".") || !password.contains("_")) {
            throw InvalidPassword.noSpecialChars();
        }
        if (password.chars().noneMatch(Character::isDigit)) {
            throw InvalidPassword.noDigit();
        }
        if (password.chars().noneMatch(Character::isUpperCase)) {
            throw InvalidPassword.noUpperCase();
        }
        return true;
    }

    public boolean validatePassportCode() {
        if (passportCode.length() >= 6) {
            throw InvalidPassportCode.invalidLength();
        }
        return true;
    }

    public boolean validatePhoneNumber() {
        Matcher phoneMatcher = Pattern.compile("^[0-9]{10}$").matcher(phoneNumber.getNumber());
        if (!phoneMatcher.find()) {
            throw InvalidPhoneNumber.invalidFormat();
        }
        return true;
    }

    private String validationCharacters(String target) {
        char[] filterChars = {'%', '$', '@', '=', '-', '/'};
        return target.chars()
                .filter(c -> new String(filterChars).indexOf(c) != -1)
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }

    public CustomerID getCustomerID() {
        return customerID;
    }
}
