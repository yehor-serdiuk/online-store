package ua.volcaniccupcake.onlinestore.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductTrackerMetricsConfiguration {

    @Bean
    public Counter createProductCounter(MeterRegistry meterRegistry) {
        return Counter.builder("api.product.created.count")
                .description("Total product created")
                .register(meterRegistry);
    }

}
