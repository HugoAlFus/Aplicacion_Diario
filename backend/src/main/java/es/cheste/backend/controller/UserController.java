package es.cheste.backend.controller;

import es.cheste.backend.dto.UserDTO;
import es.cheste.backend.dto.UserLoginDTO;
import es.cheste.backend.dto.UserRegisterDTO;
import es.cheste.backend.dto.UserUpdatePasswordDTO;
import es.cheste.backend.model.User;
import es.cheste.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar los usuarios.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Registra un nuevo usuario.
     *
     * @param userDTO los datos del usuario a registrar.
     * @return la respuesta con los datos del usuario registrado.
     * @throws RuntimeException si ocurre un error durante el registro.
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegisterDTO userDTO) throws RuntimeException{

        User user = userService.registerUser(userDTO);

        UserDTO responseDTO = new UserDTO(user.getUsername(), user.getEmail());

        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Inicia sesión un usuario.
     *
     * @param loginDTO los datos de inicio de sesión del usuario.
     * @return la respuesta con los datos del usuario autenticado.
     */
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserLoginDTO loginDTO){
        User user = userService.loginUser(loginDTO);
        UserDTO responseDTO = new UserDTO(user.getUsername(), user.getEmail());
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Actualiza la contraseña de un usuario.
     *
     * @param userDTO los nuevos datos de la contraseña del usuario.
     * @return la respuesta con los datos del usuario actualizado.
     */
    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserUpdatePasswordDTO userDTO) {
        User user = userService.updateUserPassword(userDTO);
        UserDTO responseDTO = new UserDTO(user.getUsername(), user.getEmail());
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Obtiene el ID de un usuario por su nombre de usuario.
     *
     * @param username el nombre de usuario.
     * @return la respuesta con el ID del usuario.
     */
    @GetMapping("/{username}/id")
    public ResponseEntity<Long> getUserIdByUsername(@PathVariable String username) {
        Long userId = userService.getUserByUsername(username);
        return ResponseEntity.ok(userId);
    }

    /**
     * Obtiene el nombre de usuario por su ID.
     *
     * @param userId el ID del usuario.
     * @return la respuesta con el nombre de usuario.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<String> getUsernameById(@PathVariable Long userId){

        String username = userService.getUserById(userId);
        return ResponseEntity.ok(username);
    }
}