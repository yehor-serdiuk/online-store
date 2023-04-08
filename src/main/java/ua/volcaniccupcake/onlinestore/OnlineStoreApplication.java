package ua.volcaniccupcake.onlinestore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ua.volcaniccupcake.onlinestore.config.AppProperties;
import ua.volcaniccupcake.onlinestore.model.ReleaseNote;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class OnlineStoreApplication {
	protected static final Logger logger = LoggerFactory.getLogger(OnlineStoreApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);
	}

	@Bean
	public Collection<ReleaseNote> loadReleaseNotes() {
		ReleaseNote note1 = ReleaseNote.builder()
				.version("1.0.0")
				.description("first version of the application")
				.build();
		ReleaseNote note2 = ReleaseNote.builder()
				.version("1.0.1")
				.description("minor bug fixes")
				.build();
		return new LinkedHashSet<>(Set.of(note1, note2));
	}

}
