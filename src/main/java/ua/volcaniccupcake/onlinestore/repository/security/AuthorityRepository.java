package ua.volcaniccupcake.onlinestore.repository.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.volcaniccupcake.onlinestore.model.security.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}
