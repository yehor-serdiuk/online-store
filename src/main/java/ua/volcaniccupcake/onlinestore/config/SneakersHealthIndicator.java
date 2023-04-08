package ua.volcaniccupcake.onlinestore.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ua.volcaniccupcake.onlinestore.repository.SneakersRepository;

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
