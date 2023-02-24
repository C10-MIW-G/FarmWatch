package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.dto.UserDto;
import com.theteapottroopers.farmwatch.exception.*;
import com.theteapottroopers.farmwatch.repository.UserRepository;
import com.theteapottroopers.farmwatch.security.user.Role;
import com.theteapottroopers.farmwatch.security.user.User;
import com.theteapottroopers.farmwatch.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * The service for the user requests
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidation userValidation;

    @Autowired
    public UserService(UserRepository userRepository, UserValidation userValidation) {
        this.userRepository = userRepository;
        this.userValidation = userValidation;
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
        userValidation.databaseCheck(userDto, userToUpdate);
        setUser(userDto, userToUpdate);
        try {
            userRepository.save(userToUpdate);
            return userToUpdate;
        } catch (Exception exception) {
            throw exception;
        }
    }

    private static void setUser(UserDto userDto, User userToUpdate) {
        userToUpdate.setFirstname(userDto.getFirstname());
        userToUpdate.setLastname(userDto.getLastname());
        userToUpdate.setUsername(userDto.getUsername());
        userToUpdate.setEmail(userDto.getEmail());
        userToUpdate.setRole(userDto.getRole());
    }

//    private void validationDatabaseCheck(UserDto userDto, User user) {
//        if (adminCount() == 1 &&
//                user.getRole().equals(Role.ROLE_ADMIN) &&
//                !userDto.getRole().equals(Role.ROLE_ADMIN)) {
//            throw new LastAdminDeletionException("This user is the last Admin");
//        }
//        if (userRepository.findUserByUsername(userDto.getUsername()).isPresent() &&
//                !userDto.getUsername().equals(user.getUsername())) {
//            throw new InputHasDuplicateException("Username is already taken");
//        }
//        if (userRepository.findUserByEmail(user.getEmail()).isPresent() &&
//                !userDto.getEmail().equals(user.getEmail())) {
//            throw new InputHasDuplicateException("Email is already taken");
//        }
//    }




}
