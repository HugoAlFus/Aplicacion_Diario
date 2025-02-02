package es.cheste.backend.service;

import es.cheste.backend.dto.UserLoginDTO;
import es.cheste.backend.dto.UserRegisterDTO;
import es.cheste.backend.dto.UserUpdatePasswordDTO;
import es.cheste.backend.exception.InvalidCredentialsException;
import es.cheste.backend.exception.UserAlreadyExistsException;
import es.cheste.backend.exception.UserNotFoundException;
import es.cheste.backend.model.User;
import es.cheste.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserRegisterDTO userDTO) {

        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new UserAlreadyExistsException("The username is already in use");
        }

        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new UserAlreadyExistsException("The email is already in use");
        }

        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(hashedPassword);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User loginUser(UserLoginDTO userLoginDTO) {

        User user = userRepository.findByUsername(userLoginDTO.getUsername());

        if (user == null || !passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Incorrect username or password");
        }
        return user;
    }

    public User updateUserPassword(Long userId, UserUpdatePasswordDTO userUpdatePasswordDTO) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (userUpdatePasswordDTO.getNewPassword() != null){
            user.setPassword(passwordEncoder.encode(userUpdatePasswordDTO.getNewPassword()));
        }

        return userRepository.save(user);
    }
}
