package ua.volcaniccupcake.onlinestore.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
}
