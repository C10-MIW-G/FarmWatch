package com.theteapottroopers.farmwatch;

import com.theteapottroopers.farmwatch.dto.UserDto;
import com.theteapottroopers.farmwatch.exception.InputHasDuplicateException;
import com.theteapottroopers.farmwatch.exception.LastAdminDeletionException;
import com.theteapottroopers.farmwatch.repository.UserRepository;
import com.theteapottroopers.farmwatch.security.user.Role;
import com.theteapottroopers.farmwatch.security.user.User;
import com.theteapottroopers.farmwatch.validation.UserValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UserValidationTests {

    @InjectMocks
    UserValidation userValidation;

    @Spy
    UserRepository userRepository;

    private User testUser;
    private UserDto testUserDto;

    @BeforeEach
    public void init(){
        testUser = User.builder()
                .id(1L)
                .username("Test User")
                .role(Role.ROLE_ADMIN)
                .email("test@email.com")
                .build();

        testUserDto = UserDto.builder()
                .id(1L)
                .username("Test User")
                .role(Role.ROLE_ADMIN)
                .email("test@mail.com")
                .build();
    }

    @Test
    @DisplayName("Last admin should not be able to change it ROLE")
    //This test mimics the last admin trying to change its role to a user
    public void lastAdmin(){
        testUserDto.setRole(Role.ROLE_USER);
        when(userRepository.countByRole(Role.ROLE_ADMIN)).thenReturn(1L);
        assertThrows(LastAdminDeletionException.class, () -> userValidation.databaseCheck(testUserDto, testUser));
    }

    @Test
    @DisplayName("A username should be unique")
    //This test mimics a user changing its username to an already existing one
    public void uniqueUsername(){
        testUserDto.setUsername("A different username");
        Optional<User> expectedUser = Optional.of(testUser);
        when(userRepository.findUserByUsername(anyString())).thenReturn(expectedUser);
        assertThrows(InputHasDuplicateException.class, () -> userValidation.databaseCheck(testUserDto, testUser));
    }

    @Test
    @DisplayName("An email address should be unique")
    //This test mimics a user changing its email to an already existing one
    public void isUniqueEmail(){
        Optional<User> expectedUser = Optional.of(testUser);
        testUserDto.setEmail("different@mail.com");
        when(userRepository.findUserByEmail(testUser.getEmail())).thenReturn(expectedUser);
        assertThrows(InputHasDuplicateException.class, () -> userValidation.databaseCheck(testUserDto, testUser));
    }

    @Test
    @DisplayName("Changes can be made when no user is present")
    //This test should run when there is no user found in the database
    public void changesCanBeMade(){
        Optional<User> expectedOptional = Optional.empty();
        when(userRepository.countByRole(Role.ROLE_ADMIN)).thenReturn(0L);
        when(userRepository.findUserByUsername(anyString())).thenReturn(expectedOptional);
        when(userRepository.findUserByEmail(testUserDto.getEmail())).thenReturn(expectedOptional);
        assertDoesNotThrow(() -> userValidation.databaseCheck(testUserDto, testUser));
    }
}
