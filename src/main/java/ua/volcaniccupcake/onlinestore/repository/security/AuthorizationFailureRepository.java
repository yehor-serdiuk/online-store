package ua.volcaniccupcake.onlinestore.repository.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.volcaniccupcake.onlinestore.model.security.AuthorizationFailure;
import ua.volcaniccupcake.onlinestore.model.security.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorizationFailureRepository extends CrudRepository<AuthorizationFailure, Long> {
    public List<AuthorizationFailure> findAllByUserAndCreatedDateIsAfter(User user, Timestamp timestamp);
}
