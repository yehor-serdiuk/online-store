package ua.volcaniccupcake.onlinestore.service;

import org.springframework.stereotype.Service;
import ua.volcaniccupcake.onlinestore.model.Customer;
import ua.volcaniccupcake.onlinestore.model.Order;
import ua.volcaniccupcake.onlinestore.model.dto.ItemDTO;
import ua.volcaniccupcake.onlinestore.model.dto.OrderDTO;
import ua.volcaniccupcake.onlinestore.model.security.User;

import java.util.List;
import java.util.Set;

public interface OrderService {

    void save(User user, OrderDTO orderDTO);

    Set<OrderDTO> listOrders(User user);

}
