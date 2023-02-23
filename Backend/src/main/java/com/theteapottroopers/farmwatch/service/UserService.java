package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.dto.UserDto;
import com.theteapottroopers.farmwatch.exception.AnimalNotFoundException;
import com.theteapottroopers.farmwatch.exception.LastAdminDeletionException;
import com.theteapottroopers.farmwatch.exception.UserNotFoundException;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.repository.UserRepository;
import com.theteapottroopers.farmwatch.security.user.Role;
import com.theteapottroopers.farmwatch.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * The service for the user requests
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers(){
        List<User> allUsers = userRepository.findAll();
        if (allUsers.size() == 0) {
            throw new UserNotFoundException("There are currently no users in the database\n\n" +
                    "Please contact the application development company");
        }
        return userRepository.findAll();
    }

    public List<User> findAllCaretakers() {
        List<User> allCareTakers = userRepository.findAllByRole(Role.ROLE_CARETAKER);
        if (allCareTakers.size() == 0) {
            throw new UserNotFoundException("There are currently no caretakers in your database\n\n" +
                    "Please assign the caretaker role");
        }
        return userRepository.findAllByRole(Role.ROLE_CARETAKER);
    }    

    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                "User by id " + id + " was not found!"));
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException(
                "User by username " + username + " was not found!"));
    }

    public User updateUser(UserDto userDto) {
        User userToUpdate = findUserById(userDto.getId());
        if (adminCount() == 1 &&
                userToUpdate.getRole().equals(Role.ROLE_ADMIN) &&
                !userDto.getRole().equals(Role.ROLE_ADMIN)) {
            throw new LastAdminDeletionException("This user is the last Admin");
        }
        User updatedUser = setAndSaveUser(userDto, userToUpdate);
        return updatedUser;
    }

    private User setAndSaveUser(UserDto userDto, User userToUpdate) {
        userToUpdate.setFirstname(userDto.getFirstname());
        userToUpdate.setLastname(userDto.getLastname());
        userToUpdate.setUsername(userDto.getUsername());
        userToUpdate.setEmail(userDto.getEmail());
        userToUpdate.setRole(userDto.getRole());
        User updatedUser = userRepository.save(userToUpdate);
        return updatedUser;
    }

    private long adminCount() {
        return userRepository.countByRole(Role.ROLE_ADMIN);
    }
}
