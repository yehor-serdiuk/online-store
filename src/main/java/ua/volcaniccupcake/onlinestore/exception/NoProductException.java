package ua.volcaniccupcake.onlinestore.exception;

public class NoProductException extends RuntimeException {
    public NoProductException() {
        this (null);
    }
    public NoProductException(Throwable cause) {
        super("There is no product in the repository", cause);
    }
}
