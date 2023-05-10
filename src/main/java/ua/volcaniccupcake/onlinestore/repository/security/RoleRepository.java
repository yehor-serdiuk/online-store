package ua.volcaniccupcake.onlinestore.repository.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.volcaniccupcake.onlinestore.model.security.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
