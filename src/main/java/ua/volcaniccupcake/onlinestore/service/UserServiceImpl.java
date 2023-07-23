package ua.volcaniccupcake.onlinestore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.volcaniccupcake.onlinestore.exception.PasswordDoesNotMatchException;
import ua.volcaniccupcake.onlinestore.exception.UserAlreadyExistsException;
import ua.volcaniccupcake.onlinestore.model.dto.UserDTO;
import ua.volcaniccupcake.onlinestore.model.mapper.UserMapper;
import ua.volcaniccupcake.onlinestore.model.security.User;
import ua.volcaniccupcake.onlinestore.repository.security.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    @Override
    public void registerNewUser(UserDTO userDTO) {
        if (userExists(userDTO)) {
            throw new UserAlreadyExistsException(userDTO);
        }
        if (!passwordMatches(userDTO)) {
            throw new PasswordDoesNotMatchException("password does not match repeat password");
        }


        User user = userMapper.userDTOToUser(userDTO);
        log.debug(user.toString());
        userRepository.save(user);
    }

    private boolean userExists(UserDTO userDTO) {
        return userRepository.existsByUsername(userDTO.getUsername());
    }
    private boolean passwordMatches(UserDTO userDTO) {
        return userDTO.getPassword()
                .equals(userDTO.getRepeatPassword());
    }
}
