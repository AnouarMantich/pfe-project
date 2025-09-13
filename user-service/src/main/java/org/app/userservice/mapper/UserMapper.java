package org.app.userservice.mapper;

import org.app.userservice.dto.UserRequestDTO;
import org.app.userservice.dto.UserResponseDTO;
import org.app.userservice.model.User;
import org.springframework.beans.BeanUtils;

public class UserMapper {

    public static User toUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        BeanUtils.copyProperties(userRequestDTO, user);
        return user;
    }


    public static UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        BeanUtils.copyProperties(user, userResponseDTO);
        return userResponseDTO;
    }

}
