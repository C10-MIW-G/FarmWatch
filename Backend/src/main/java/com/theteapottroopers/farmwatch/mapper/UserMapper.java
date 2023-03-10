package com.theteapottroopers.farmwatch.mapper;

import com.theteapottroopers.farmwatch.dto.UserLeanDto;
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

    public UserDto toUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getUsername(),
                user.getRole());
    }

    public List<UserDto> toUserDtos(List<User> users){
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: users) {
            userDtos.add(new UserDto(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getRole())
            );
        }
        return userDtos;
    }

    public List<UserLeanDto> toCaretakerLeanDtos(List<User> users){
        List<UserLeanDto> caretakerLeanDtos = new ArrayList<>();
        for (User user: users) {
            UserLeanDto caretakerLeanDto = new UserLeanDto();
            caretakerLeanDto.setId(user.getId());
            caretakerLeanDto.setFullName(user.getFullName());
            caretakerLeanDtos.add(caretakerLeanDto);
        }
        return caretakerLeanDtos;
    }

}
