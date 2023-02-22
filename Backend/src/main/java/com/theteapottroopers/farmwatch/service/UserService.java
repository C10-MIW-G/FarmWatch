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
        return userRepository.findAll();
    }

    public List<User> findAllCaretakers() {
        List<User> usersToFilter = userRepository.findAll();
        List<User> allCaretakers = new ArrayList<>();
        for (User user: usersToFilter) {
            if (user.getRole().equals(Role.ROLE_CARETAKER)){
                allCaretakers.add(user);
            }
        }
        return allCaretakers;
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
        User userToUpdate = userRepository.findById(userDto.getId()).get();
        if (adminCount() > 1 ||
                        (adminCount() == 1 &&
                        userDto.getRole().equals(Role.ROLE_ADMIN))){
            User updatedUser = setAndSaveUser(userDto, userToUpdate);
            return updatedUser;
        } else {
            throw new LastAdminDeletionException("This user is the last Admin");
        }
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
