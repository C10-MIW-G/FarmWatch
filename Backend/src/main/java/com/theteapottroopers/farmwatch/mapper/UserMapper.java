package com.theteapottroopers.farmwatch.mapper;

import com.theteapottroopers.farmwatch.dto.UserDto;
import com.theteapottroopers.farmwatch.security.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * This class returns a User or UserDto when asked
 */

public class UserMapper {

    public UserDto toUserDTO(User user){
        return new UserDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getEmail(),
                user.getRole());
    }

    public List<UserDto> toUserDtos(List<User> users){
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: users) {
            userDtos.add(new UserDto(
                    user.getId(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail(),
                    user.getEmail(),
                    user.getRole())
            );
        }
        return userDtos;
    }

}
