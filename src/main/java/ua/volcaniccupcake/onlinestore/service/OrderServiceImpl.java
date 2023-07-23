package ua.volcaniccupcake.onlinestore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.volcaniccupcake.onlinestore.exception.InvalidOrderException;
import ua.volcaniccupcake.onlinestore.model.Item;
import ua.volcaniccupcake.onlinestore.model.Order;
import ua.volcaniccupcake.onlinestore.model.dto.ItemDTO;
import ua.volcaniccupcake.onlinestore.model.dto.OrderDTO;
import ua.volcaniccupcake.onlinestore.model.mapper.ItemMapper;
import ua.volcaniccupcake.onlinestore.model.mapper.OrderMapper;
import ua.volcaniccupcake.onlinestore.model.security.User;
import ua.volcaniccupcake.onlinestore.repository.ItemRepository;
import ua.volcaniccupcake.onlinestore.repository.OrderRepository;
import ua.volcaniccupcake.onlinestore.repository.ProductRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;


    @Override
    public void save(User user, OrderDTO orderDTO) {
        if (!isValid(orderDTO)) {
            throw new InvalidOrderException("invalid order", orderDTO);
        }

        Order order = orderRepository.save(Order.builder()
                .customer(user.getCustomer())
                .build());

        Set<Item> itemSet = itemMapper.itemDTOSetToItemSet(orderDTO.getItems());
        itemSet.forEach(item -> item.setOrder(order));
        itemRepository.saveAll(itemSet);
    }

    @Override
    public Set<OrderDTO> listOrders(User user) {
        Set<Order> orderSet = orderRepository.findAllByCustomer_Id(user.getCustomer().getId());


        return orderMapper.orderSetToOrderDTOSet(orderSet);
    }

    private boolean isValid(OrderDTO orderDTO) {
        for (ItemDTO itemDTO : orderDTO.getItems()) {
            if (!productRepository.existsById(itemDTO.getProduct().getId())) {
                return false;
            }
        }

        return true;
    }
}
