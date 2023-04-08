package ua.volcaniccupcake.onlinestore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

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
}
