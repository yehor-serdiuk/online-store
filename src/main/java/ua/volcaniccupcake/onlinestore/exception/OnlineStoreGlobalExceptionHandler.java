package ua.volcaniccupcake.onlinestore.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OnlineStoreGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {SneakersNotFoundException.class})
    public ResponseEntity<?> handleSneakersNotFound(
            SneakersNotFoundException sneakersNotFoundException,
            WebRequest request) {
        return super.handleExceptionInternal(sneakersNotFoundException, sneakersNotFoundException.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
