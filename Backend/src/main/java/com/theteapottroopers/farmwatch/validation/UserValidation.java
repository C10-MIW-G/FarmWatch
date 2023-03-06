package com.theteapottroopers.farmwatch.validation;

import com.theteapottroopers.farmwatch.dto.UserDto;
import com.theteapottroopers.farmwatch.exception.InputHasDuplicateException;
import com.theteapottroopers.farmwatch.exception.LastAdminDeletionException;
import com.theteapottroopers.farmwatch.repository.UserRepository;
import com.theteapottroopers.farmwatch.security.user.Role;
import com.theteapottroopers.farmwatch.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * this class validates data for User
 */

@Service
public class UserValidation {

    private final UserRepository userRepository;

    @Autowired
    public UserValidation(UserRepository userRepository) {this.userRepository = userRepository;}

    public void databaseCheck(UserDto userDto, User user) {
        lastAdmin(userDto,user);
        uniqueUsername(userDto,user);
        uniqueEmail(userDto,user);
    }

    private void lastAdmin(UserDto userDto, User user) {
        if (userRepository.countByRole(Role.ROLE_ADMIN) == 1 &&
                user.getRole().equals(Role.ROLE_ADMIN) &&
                !userDto.getRole().equals(Role.ROLE_ADMIN)) {
            throw new LastAdminDeletionException("This user is the last admin");
        }
    }

    private void uniqueUsername(UserDto userDto, User user) {
        if (userRepository.findUserByUsername(userDto.getUsername()).isPresent() &&
                !userDto.getUsername().equals(user.getUsername())) {
            throw new InputHasDuplicateException("Username already exists");
        }
    }

    private void uniqueEmail(UserDto userDto, User user) {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent() &&
                !userDto.getEmail().equals(user.getEmail())) {
            throw new InputHasDuplicateException("An account with Email " + userDto.getEmail() +" already exists.");
        }
    }







}
