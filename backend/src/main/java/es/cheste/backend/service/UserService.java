package es.cheste.backend.service;

import es.cheste.backend.dto.UserLoginDTO;
import es.cheste.backend.dto.UserRegisterDTO;
import es.cheste.backend.dto.UserUpdatePasswordDTO;
import es.cheste.backend.exception.InvalidCredentialsException;
import es.cheste.backend.exception.UserAlreadyExistsException;
import es.cheste.backend.exception.UserNotFoundException;
import es.cheste.backend.model.User;
import es.cheste.backend.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Servicio para gestionar las operaciones relacionadas con los usuarios.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
@Service
public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registra un nuevo usuario.
     *
     * @param userDTO los datos del usuario a registrar.
     * @return el usuario registrado.
     * @throws UserAlreadyExistsException si el nombre de usuario o el correo electrónico ya están en uso.
     */
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
        user.setCreatedAt(LocalDate.now());

        return userRepository.save(user);
    }

    /**
     * Inicia sesión de un usuario.
     *
     * @param userLoginDTO los datos de inicio de sesión del usuario.
     * @return el usuario que ha iniciado sesión.
     * @throws UserNotFoundException       si el nombre de usuario es incorrecto.
     * @throws InvalidCredentialsException si la contraseña es incorrecta.
     */
    public User loginUser(UserLoginDTO userLoginDTO) {

        User user = userRepository.findByUsername(userLoginDTO.getUsername());

        if (user == null) {
            throw new UserNotFoundException("Incorrect username or password");
        }

        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Incorrect username or password");
        }
        return user;
    }

    /**
     * Actualiza la contraseña de un usuario.
     *
     * @param userUpdatePasswordDTO los datos para actualizar la contraseña del usuario.
     * @return el usuario con la contraseña actualizada.
     * @throws UserNotFoundException si el usuario no se encuentra.
     * @throws InvalidCredentialsException si la nueva contraseña ya está en uso.
     */
    public User updateUserPassword(UserUpdatePasswordDTO userUpdatePasswordDTO) {

        System.out.println("email: " + userUpdatePasswordDTO.getEmail());
        User user = userRepository.findByEmail(userUpdatePasswordDTO.getEmail());

        System.out.println("User " + user);
        System.out.println(userUpdatePasswordDTO);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        if (!userUpdatePasswordDTO.getPassword().equals(userUpdatePasswordDTO.getConfirmPassword())) {
            throw new InvalidCredentialsException("The must be the same");
        }
        user.setPassword(passwordEncoder.encode(userUpdatePasswordDTO.getConfirmPassword()));

        return userRepository.save(user);
    }

    /**
     * Obtiene el nombre de usuario por su ID.
     *
     * @param userId el ID del usuario.
     * @return el nombre de usuario.
     * @throws UserNotFoundException si el usuario no se encuentra.
     */
    public String getUserById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("The user id do not exist"));

        return user.getUsername();
    }

    /**
     * Obtiene el ID del usuario por su nombre de usuario.
     *
     * @param username el nombre de usuario.
     * @return el ID del usuario.
     * @throws UserNotFoundException si el usuario no se encuentra.
     */
    public Long getUserByUsername(String username) {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        return user.getId();
    }
}