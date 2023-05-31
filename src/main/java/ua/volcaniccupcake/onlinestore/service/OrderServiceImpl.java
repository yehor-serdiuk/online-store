package ua.volcaniccupcake.onlinestore.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.volcaniccupcake.onlinestore.exception.InvalidOrderException;
import ua.volcaniccupcake.onlinestore.model.Customer;
import ua.volcaniccupcake.onlinestore.model.Item;
import ua.volcaniccupcake.onlinestore.model.Order;
import ua.volcaniccupcake.onlinestore.model.dto.OrderDTO;
import ua.volcaniccupcake.onlinestore.model.dto.ProductDTO;
import ua.volcaniccupcake.onlinestore.model.mapper.ItemMapper;
import ua.volcaniccupcake.onlinestore.model.mapper.OrderMapper;
import ua.volcaniccupcake.onlinestore.repository.ItemRepository;
import ua.volcaniccupcake.onlinestore.repository.OrderRepository;
import ua.volcaniccupcake.onlinestore.util.OrderDTOValidator;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderDTOValidator orderDTOValidator;


    @Override
    public void save(Customer customer, OrderDTO orderDTO) {
        if (!orderDTOValidator.isValid(orderDTO)) {
            throw new InvalidOrderException("invalid order", orderDTO);
        }

        Order order = orderRepository.save(Order.builder()
                .customer(customer)
                .build());

        Set<Item> itemSet = itemMapper.itemDTOSetToItemSet(orderDTO.getItems());
        itemSet.forEach(item -> item.setOrder(order));
        itemRepository.saveAll(itemSet);
    }

    @Override
    public Set<OrderDTO> listOrdersByCustomerId(long customerId) {
        Set<Order> orderSet = orderRepository.findAllByCustomer_Id(customerId);


        return orderMapper.orderSetToOrderDTOSet(orderSet);
    }

}
