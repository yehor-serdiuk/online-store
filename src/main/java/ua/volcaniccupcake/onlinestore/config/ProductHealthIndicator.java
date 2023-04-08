package ua.volcaniccupcake.onlinestore.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ua.volcaniccupcake.onlinestore.repository.ProductRepository;

@Component
@AllArgsConstructor
public class ProductHealthIndicator implements HealthIndicator {

    private ProductRepository productRepository;

    @Override
    public Health health() {
        if ( productRepository.count() == 0)
            return Health.down().withDetail("status", "repository empty").build();
        else
            return Health.up().build();
    }
}
