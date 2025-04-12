package com.rolenroll.rnr_app.mappers;

import com.rolenroll.rnr_app.dto.UserDTO;
import com.rolenroll.rnr_app.entities.User;

public class UserMapper {

    public static UserDTO toDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
