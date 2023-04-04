package ua.volcaniccupcake.onlinestore;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SneakersRepository extends CrudRepository<Sneakers, Long> {

    List<Sneakers> findAllByName(String name);
}
