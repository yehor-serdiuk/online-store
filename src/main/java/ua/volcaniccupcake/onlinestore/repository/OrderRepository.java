package ua.volcaniccupcake.onlinestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.volcaniccupcake.onlinestore.model.Order;
import ua.volcaniccupcake.onlinestore.model.security.Role;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}

