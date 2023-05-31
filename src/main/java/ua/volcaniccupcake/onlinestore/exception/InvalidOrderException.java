package ua.volcaniccupcake.onlinestore.exception;

import lombok.Getter;
import ua.volcaniccupcake.onlinestore.model.dto.OrderDTO;

@Getter
public class InvalidOrderException extends RuntimeException {
    OrderDTO orderDTO;

    public InvalidOrderException(String message) {
        super(message);
    }

    public InvalidOrderException(String message, OrderDTO orderDTO) {
        this(message);
        this.orderDTO = orderDTO;
    }
}
