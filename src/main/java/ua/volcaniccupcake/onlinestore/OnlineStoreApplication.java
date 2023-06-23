package ua.volcaniccupcake.onlinestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ua.volcaniccupcake.onlinestore.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class OnlineStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);
	}

}
