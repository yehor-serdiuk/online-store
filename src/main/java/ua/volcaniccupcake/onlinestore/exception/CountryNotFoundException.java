package ua.volcaniccupcake.onlinestore.exception;

import ua.volcaniccupcake.onlinestore.model.Country;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String message) {
        super (message);
    }
}
