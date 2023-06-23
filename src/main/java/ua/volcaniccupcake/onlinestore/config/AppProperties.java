package ua.volcaniccupcake.onlinestore.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.properties")
@Getter
@Setter
public class AppProperties {
    private String name;
    private String description;
    private String version;
}
