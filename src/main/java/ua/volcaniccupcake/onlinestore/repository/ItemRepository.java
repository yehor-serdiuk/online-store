package ua.volcaniccupcake.onlinestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.volcaniccupcake.onlinestore.model.Item;
import ua.volcaniccupcake.onlinestore.model.security.Role;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
