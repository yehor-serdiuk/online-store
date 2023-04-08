package ua.volcaniccupcake.onlinestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.volcaniccupcake.onlinestore.model.Sneakers;

import java.util.List;

@Repository
public interface SneakersRepository extends CrudRepository<Sneakers, Long> {

    List<Sneakers> findAllByName(String name);
}
