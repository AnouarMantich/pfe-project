package org.app.userservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.app.userservice.dto.UserRequestDTO;
import org.app.userservice.dto.UserResponseDTO;
import org.app.userservice.exception.UserNotFoundException;
import org.app.userservice.mapper.UserMapper;
import org.app.userservice.model.User;
import org.app.userservice.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO getUser(String cin) throws UserNotFoundException {
        User user = userRepository.findById(cin)
                .orElseThrow(
                        () -> new UserNotFoundException("User not found with id " + cin)
                );
        return UserMapper.toUserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
       return userRepository.findAll().stream().map(UserMapper::toUserResponseDTO).toList();

    }

    @Override
    public Boolean checkUserByKeyCloakId(String keycloakId) {
        return userRepository.existsByKeycloakId(keycloakId);
    }

    @Override
    public UserResponseDTO updateUser(String userId, UserRequestDTO userRequestDTO) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found with id " + userId)
        );

        if (userRequestDTO.getFirstName() != null) {
            user.setFirstName(userRequestDTO.getFirstName());
        }
        if (userRequestDTO.getLastName() != null) {
            user.setLastName(userRequestDTO.getLastName());
        }
        if (userRequestDTO.getEmail() != null) {
            user.setEmail(userRequestDTO.getEmail());
        }


        return UserMapper.toUserResponseDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(String userId) {
         userRepository.deleteById(userId);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User saved = userRepository.save(UserMapper.toUser(userRequestDTO));
        return UserMapper.toUserResponseDTO(saved);
    }


}
