package ua.volcaniccupcake.onlinestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.volcaniccupcake.onlinestore.model.Customer;
import ua.volcaniccupcake.onlinestore.model.security.Role;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
