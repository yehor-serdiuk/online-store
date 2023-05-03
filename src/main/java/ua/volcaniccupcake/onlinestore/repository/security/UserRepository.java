package ua.volcaniccupcake.onlinestore.repository.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.volcaniccupcake.onlinestore.model.security.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
