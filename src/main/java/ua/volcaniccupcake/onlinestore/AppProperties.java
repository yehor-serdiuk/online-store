package ua.volcaniccupcake.onlinestore;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("app.properties")
@AllArgsConstructor
public class AppProperties {
    private String name;
    private String description;
    private String version;

}
