package ua.volcaniccupcake.onlinestore;

public class NoSneakersException extends RuntimeException {
    public NoSneakersException() {
        this (null);
    }
    public NoSneakersException(Throwable cause) {
        super("There are no sneakers in the repository", cause);
    }
}
