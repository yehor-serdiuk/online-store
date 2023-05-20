package ua.volcaniccupcake.onlinestore.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.volcaniccupcake.onlinestore.model.security.User;
import ua.volcaniccupcake.onlinestore.repository.security.AuthorizationFailureRepository;
import ua.volcaniccupcake.onlinestore.repository.security.UserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserUnlockService {
    private final UserRepository userRepository;
    private final AuthorizationFailureRepository failureRepository;

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    public void unlockUsers() {
        Set<User> lockedUsers = userRepository.findAllByAccountNonLocked(false);
        lockedUsers.forEach((user) -> user.setAccountNonLocked(true));
        userRepository.saveAll(lockedUsers);
    }

}
