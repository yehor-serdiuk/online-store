package ua.volcaniccupcake.onlinestore.exception;

public class PasswordDoesNotMatchException extends RuntimeException{
    public PasswordDoesNotMatchException(String message) {
        super(message);
    }
}
