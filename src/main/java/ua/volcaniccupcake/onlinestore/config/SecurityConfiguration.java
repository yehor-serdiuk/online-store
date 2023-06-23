package ua.volcaniccupcake.onlinestore.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import ua.volcaniccupcake.onlinestore.security.JpaUserDetailsService;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JpaUserDetailsService jpaUserDetailsService;
    //private final PersistentTokenRepository persistentTokenRepository;


   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http.authorizeHttpRequests()
               .requestMatchers(HttpMethod.GET, "/product/**").hasRole("USER")
               .requestMatchers("/h2-console/**").permitAll()
               .requestMatchers(HttpMethod.POST, "/api/user").permitAll()
               .anyRequest().authenticated()
               .and()
               .httpBasic()
               .and()
               //.rememberMe()
               //     .tokenRepository(persistentTokenRepository)
               //     .userDetailsService(jpaUserDetailsService)
               //.rememberMe()
               //     .key("mega-key")
               //    .userDetailsService(jpaUserDetailsService)
               //.and()
               .csrf().disable()
               .headers().frameOptions().sameOrigin();
        return http.build();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password-admin")
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("bob")
                .password("password-bob")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }*/

    /*@Bean
    public void userDetailsManager(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
               .withUser("spring")
               .password("{noop}password-spring")
               .roles("ADMIN")
               .and()
               .withUser("wall")
               .password("{noop}password-wall")
               .roles("USER");
    }*/
}
