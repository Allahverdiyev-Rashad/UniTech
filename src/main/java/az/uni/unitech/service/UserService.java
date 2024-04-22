package az.uni.unitech.service;

import az.uni.unitech.dto.response.UserResponse;
import az.uni.unitech.error.ErrorMessage;
import az.uni.unitech.error.exception.DataNotFoundException;
import az.uni.unitech.error.exception.IncorrectPasswordException;
import az.uni.unitech.error.exception.UserAlreadyExistsException;
import az.uni.unitech.repository.UserRepository;
import az.uni.unitech.domain.User;
import az.uni.unitech.dto.request.UserLoginRequest;
import az.uni.unitech.dto.request.UserRegistration;
import az.uni.unitech.mapper.UserMapper;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse registerUser(UserRegistration userRegistration) throws
            UserAlreadyExistsException {
        log.info("userRegistration {}", userRegistration);
        if (userRepository.findByEmailIgnoreCase(userRegistration.getEmail()).isPresent() ||
                userRepository.findByPinEqualsIgnoreCase(userRegistration.getPin()).isPresent()) {
            throw UserAlreadyExistsException.getInstance(ErrorMessage.USER_ALREADY_EXISTS);
        }
        User user = userMapper.toDomain(userRegistration);
        user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    public String loginUser(UserLoginRequest userLoginRequest) {
        log.info("userLoginRequest {}", userLoginRequest);
        Optional<User> optionalUser = userRepository.findByPinEqualsIgnoreCase(userLoginRequest.getPin());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
                return jwtService.generate(user);
            }
            throw IncorrectPasswordException.getInstance(ErrorMessage.USER_CREDENTIALS_NOT_VALID);
        }
        throw DataNotFoundException.getInstance(ErrorMessage.NOT_FOUND.formatted("User"));
    }

}