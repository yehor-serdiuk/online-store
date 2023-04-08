package ua.volcaniccupcake.onlinestore.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SneakersTrackerMetricsConfiguration {

    @Bean
    public Counter createSneakersCounter(MeterRegistry meterRegistry) {
        return Counter.builder("api.sneakers.created.count")
                .description("Total number of sneakers created")
                .register(meterRegistry);
    }

}
