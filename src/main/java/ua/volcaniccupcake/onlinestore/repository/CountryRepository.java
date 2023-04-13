package ua.volcaniccupcake.onlinestore.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.volcaniccupcake.onlinestore.model.Country;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
    List<Country> findAllByName(String name);
    Country findByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE country c SET c.name=:name WHERE c.id=:id")
    int updateCountryNameById(@Param("id") Long id, @Param("name") String name);
}
