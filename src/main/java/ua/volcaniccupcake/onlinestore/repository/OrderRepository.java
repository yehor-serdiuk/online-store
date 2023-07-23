package ua.volcaniccupcake.onlinestore.repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.volcaniccupcake.onlinestore.model.Order;
import ua.volcaniccupcake.onlinestore.model.security.Role;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Set<Order> findAllByCustomer_Id(long customerId);


}

