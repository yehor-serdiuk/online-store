package ua.volcaniccupcake.onlinestore.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import ua.volcaniccupcake.onlinestore.model.security.User;

@Slf4j
@Component
public class AuthenticationSuccessEventListener {

    @EventListener()
    public void listen(AuthenticationSuccessEvent event) {
        if (event.getSource() instanceof UsernamePasswordAuthenticationToken token) {
            if (token.getPrincipal() instanceof User user) {
                log.info("User logged: " + user.getUsername());
            }
            if (token.getDetails() instanceof WebAuthenticationDetails details) {
                log.info("IP adress: " + details.getRemoteAddress());
            }
        }
    }
}
