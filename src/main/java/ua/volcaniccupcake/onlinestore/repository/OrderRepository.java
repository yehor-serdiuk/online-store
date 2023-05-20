package ua.volcaniccupcake.onlinestore.repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.volcaniccupcake.onlinestore.model.Order;
import ua.volcaniccupcake.onlinestore.model.security.Role;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    public Iterable<Order> findAllByCustomer_Id(long customerId);

    public Iterable<Order> findAllByCustomer_IdAndId(long customerId, long id);
}

