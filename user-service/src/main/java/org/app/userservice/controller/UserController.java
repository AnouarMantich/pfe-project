package org.app.userservice.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.app.userservice.dto.UserRequestDTO;
import org.app.userservice.dto.UserResponseDTO;
import org.app.userservice.exception.UserNotFoundException;
import org.app.userservice.groupeValidator.OnCreate;
import org.app.userservice.groupeValidator.OnUpdate;
import org.app.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;


    @GetMapping("/{cin}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String cin) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUser(cin));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUser() {

        return ResponseEntity.ok(userService.getAllUsers());
    }



    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser( @RequestBody @Validated(OnCreate.class) UserRequestDTO userRequestDTO)  {

        return ResponseEntity.ok(userService.createUser(userRequestDTO));
    }

    @GetMapping("/exist/{keycloakId}")
    public ResponseEntity<Boolean> checkUserByKeyCloakId(@PathVariable String keycloakId){
        return ResponseEntity.ok(userService.checkUserByKeyCloakId(keycloakId));
    }

    @PutMapping("/{cin}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String cin, @RequestBody @Validated(OnUpdate.class) UserRequestDTO userRequestDTO) throws UserNotFoundException {
        return ResponseEntity.ok(userService.updateUser(cin, userRequestDTO));
    }
    @DeleteMapping("/{cin}")
    public ResponseEntity<Object> deleteUser(@PathVariable String cin){
        userService.deleteUser(cin);
        return ResponseEntity.ok().build();
    }

}
