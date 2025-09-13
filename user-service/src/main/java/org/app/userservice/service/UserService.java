package org.app.userservice.service;

import org.app.userservice.dto.UserRequestDTO;
import org.app.userservice.dto.UserResponseDTO;
import org.app.userservice.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {


    UserResponseDTO getUser(String cin) throws UserNotFoundException;

    List<UserResponseDTO> getAllUsers();

    Boolean checkUserByKeyCloakId(String keycloakId);

    UserResponseDTO updateUser(String cin, UserRequestDTO userRequestDTO) throws UserNotFoundException;

    void deleteUser(String cin);

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
}
