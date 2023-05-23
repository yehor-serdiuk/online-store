package ua.volcaniccupcake.onlinestore.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.volcaniccupcake.onlinestore.model.Customer;
import ua.volcaniccupcake.onlinestore.model.Item;
import ua.volcaniccupcake.onlinestore.model.Order;
import ua.volcaniccupcake.onlinestore.model.dto.ItemDTO;
import ua.volcaniccupcake.onlinestore.model.dto.OrderDTO;
import ua.volcaniccupcake.onlinestore.model.mapper.ItemMapper;
import ua.volcaniccupcake.onlinestore.model.mapper.OrderMapper;
import ua.volcaniccupcake.onlinestore.repository.ItemRepository;
import ua.volcaniccupcake.onlinestore.repository.OrderRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;


    @Override
    public OrderDTO save(Customer customer, OrderDTO orderDTO) {

        Order order = orderRepository.save(Order.builder()
                .customer(customer)
                .build());

        Set<Item> itemSet = itemMapper.itemDTOSetToItemSet(new HashSet<>(orderDTO.getItems()));
        itemSet.forEach(item -> item.setOrder(order));
        itemRepository.saveAll(itemSet);

        return orderMapper.orderToOrderDTO(order);
    }

    @Override
    public Set<OrderDTO> listOrdersByCustomerId(long customerId) {
        Set<Order> orderSet = orderRepository.findAllByCustomer_Id(customerId);


        return orderMapper.orderSetToOrderDTOSet(orderSet);
    }
}
