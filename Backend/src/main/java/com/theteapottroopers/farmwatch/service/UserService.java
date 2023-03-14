package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.dto.UserDto;
import com.theteapottroopers.farmwatch.exception.*;
import com.theteapottroopers.farmwatch.repository.ForgotPasswordRepository;
import com.theteapottroopers.farmwatch.repository.UserRepository;
import com.theteapottroopers.farmwatch.repository.VerificationRepository;
import com.theteapottroopers.farmwatch.security.auth.ForgotPasswordToken;
import com.theteapottroopers.farmwatch.security.auth.VerificationToken;
import com.theteapottroopers.farmwatch.security.user.Role;
import com.theteapottroopers.farmwatch.security.user.User;
import com.theteapottroopers.farmwatch.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.theteapottroopers.farmwatch.service.AnimalService.MESSAGE_FOR_UNKNOWN_EXCEPTION;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * The service for the user requests
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidation userValidation;
    private final VerificationRepository verificationRepository;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserValidation userValidation, VerificationRepository verificationRepository,
                       ForgotPasswordRepository forgotPasswordRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userValidation = userValidation;
        this.verificationRepository = verificationRepository;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.passwordEncoder = passwordEncoder;
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
            throw new SomethingWentWrongException(MESSAGE_FOR_UNKNOWN_EXCEPTION);
        }
    }

    private static void setUser(UserDto userDto, User userToUpdate) {
        userToUpdate.setFullName(userDto.getFullName());
        userToUpdate.setUsername(userDto.getUsername());
        userToUpdate.setEmail(userDto.getEmail());
        userToUpdate.setRole(userDto.getRole());
    }

    public void createVerificationToken(User user, String token){
        VerificationToken newToken = new VerificationToken(token, user);
        verificationRepository.save(newToken);
    }

    public VerificationToken getVerificationToken(String verificationToken){
        Optional<VerificationToken> verificationTokenOptional = verificationRepository.findByToken(verificationToken);
        return verificationTokenOptional.orElse(null);
    }

    public void removeVerificationToken(VerificationToken token){
        verificationRepository.deleteById(token.getId());
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email).orElse(null);
    }

    public void createForgotPasswordToken(User user, String token){
        ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken(token, user);
        forgotPasswordRepository.save(forgotPasswordToken);
    }

    public ForgotPasswordToken getForgotPasswordToken(String forgotPasswordToken){
        Optional<ForgotPasswordToken> forgotPasswordTokenOptional = forgotPasswordRepository.findByToken(forgotPasswordToken);
        return forgotPasswordTokenOptional.orElse(null);
    }

    public void removeForgotPasswordToken(ForgotPasswordToken token){
        forgotPasswordRepository.deleteById(token.getId());
    }

    public void changePassword(User user, String password){
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
