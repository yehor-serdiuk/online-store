package ua.volcaniccupcake.onlinestore.controller;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.volcaniccupcake.onlinestore.exception.*;

@ControllerAdvice
public class OnlineStoreGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<?> handleProductNotFound(
            ProductNotFoundException ProductNotFoundException,
            WebRequest request) {
        return super.handleExceptionInternal(ProductNotFoundException, ProductNotFoundException.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {InvalidOrderException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleInvalidOrder(InvalidOrderException invalidOrderException,
                                                WebRequest request) {
        return super.handleExceptionInternal(invalidOrderException, invalidOrderException.getMessage(),
                                            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleUserAlreadyExists(UserAlreadyExistsException userAlreadyExistsException,
                                                     WebRequest request) {
        return super.handleExceptionInternal(userAlreadyExistsException, "user already exists",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {PasswordDoesNotMatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handlePasswordDoesNotMatch(PasswordDoesNotMatchException passwordDoesNotMatchException,
                                                        WebRequest request) {
        return super.handleExceptionInternal(passwordDoesNotMatchException, "passwords do not match",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {CountryNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handlePasswordDoesNotMatch(CountryNotFoundException countryNotFoundException,
                                                        WebRequest request) {
        return super.handleExceptionInternal(countryNotFoundException, "country not found",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
