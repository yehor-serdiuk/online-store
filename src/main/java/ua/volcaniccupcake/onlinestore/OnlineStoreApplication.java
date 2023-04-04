package ua.volcaniccupcake.onlinestore;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class OnlineStoreApplication {
	protected static final Logger logger = LoggerFactory.getLogger(OnlineStoreApplication.class);


	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(OnlineStoreApplication.class, args);
		AppService service = applicationContext.getBean(AppService.class);
		logger.info(String.format("store name: %s", service.getAppProperties().getName()));
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
	@Bean
	public OpenAPI customOpenAPI(AppProperties appProperties) {

		return new OpenAPI().info(new Info()
				.title("Online store API")
				.description(appProperties.getDescription())
				.version(appProperties.getVersion())
				.termsOfService("http://swagger.io/terms/")
				.license(new License()
						.name("GPLv3")
						.url("https://www.gnu.org/licenses/gpl-3.0.html")));
	}

	/*@Bean
	public UserDetailsService users() {
		User.UserBuilder users = User.withDefaultPasswordEncoder();
		UserDetails user = users
			.username("user")
			.password("password")
			.roles("USER")
			.build();
		UserDetails admin = users
			.username("admin")
			.password("password")
			.roles("ADMIN")
			.build();
		return new InMemoryUserDetailsManager(user, admin);
	}*/

}
