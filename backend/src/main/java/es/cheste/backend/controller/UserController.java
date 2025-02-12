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

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegisterDTO userDTO) throws RuntimeException{

        User user = userService.registerUser(userDTO);

        UserDTO responseDTO = new UserDTO(user.getUsername(), user.getEmail());

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserLoginDTO loginDTO){
        User user = userService.loginUser(loginDTO);
        UserDTO responseDTO = new UserDTO(user.getUsername(), user.getEmail());
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserUpdatePasswordDTO userDTO) {
        User user = userService.updateUserPassword(userDTO);
        UserDTO responseDTO = new UserDTO(user.getUsername(), user.getEmail());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{username}/id")
    public ResponseEntity<Long> getUserIdByUsername(@PathVariable String username) {
        Long userId = userService.getUserByUsername(username);
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<String> getUsernameById(@PathVariable Long userId){

        String username = userService.getUserById(userId);
        return ResponseEntity.ok(username);
    }
}
