package ua.volcaniccupcake.onlinestore;

import lombok.AllArgsConstructor;
import org.hibernate.mapping.List;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@AllArgsConstructor
public class SneakersHealthIndicator implements HealthIndicator {

    private SneakersRepository sneakersRepository;

    @Override
    public Health health() {
        if ( sneakersRepository.count() == 0)
            return Health.down().withDetail("status", "repository empty").build();
        else
            return Health.up().build();
    }
}
