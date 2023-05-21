package ua.volcaniccupcake.onlinestore.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.volcaniccupcake.onlinestore.model.Order;
import ua.volcaniccupcake.onlinestore.model.dto.OrderDTO;

import java.util.Set;

@Mapper(uses = ItemMapper.class)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO orderToOrderDTO(Order order);

    Set<OrderDTO> orderSetToOrderDTOSet(Set<Order> orderSet);
}
