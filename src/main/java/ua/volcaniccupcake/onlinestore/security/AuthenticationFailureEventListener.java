package ua.volcaniccupcake.onlinestore.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import ua.volcaniccupcake.onlinestore.model.security.AuthorizationFailure;
import ua.volcaniccupcake.onlinestore.model.security.User;
import ua.volcaniccupcake.onlinestore.repository.security.AuthorizationFailureRepository;
import ua.volcaniccupcake.onlinestore.repository.security.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationFailureEventListener {
    private final UserRepository userRepository;
    private final AuthorizationFailureRepository failureRepository;

    @EventListener
    public void listen(AuthenticationFailureBadCredentialsEvent event) {
        log.info("Authorization failure");
        if (event.getSource() instanceof UsernamePasswordAuthenticationToken token) {
            AuthorizationFailure.AuthorizationFailureBuilder builder = AuthorizationFailure.builder();
            if (token.getPrincipal() instanceof String username) {
                userRepository.findByUsername(username).ifPresent(builder::user);
            }
            if (token.getDetails() instanceof WebAuthenticationDetails details) {
                builder.sourceIp(details.getRemoteAddress());
            }

            AuthorizationFailure failure = failureRepository.save(builder.build());

            if (failure.getUser() != null) {
                lockUserAccount(failure.getUser());
            }
        }
    }

    private void lockUserAccount(User user) {
        List<AuthorizationFailure> failureList = failureRepository.findAllByUserAndCreatedDateIsAfter(user, Timestamp.valueOf(LocalDateTime.now().minusHours(5)));

        if (failureList.size() > 3) {
            user.setAccountNonLocked(false);
            userRepository.save(user);
        }

    }
}
