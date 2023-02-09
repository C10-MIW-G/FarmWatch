package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.dto.AnimalDto;
import com.theteapottroopers.farmwatch.dto.UserDto;
import com.theteapottroopers.farmwatch.mapper.UserMapper;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.security.user.User;
import com.theteapottroopers.farmwatch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Handles user requests
 */
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class UserResource {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserResource(UserService userService) {
        this.userService = userService;
        this.userMapper = new UserMapper();
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<User> users = userService.findAllUsers();
        List<UserDto> userDtos = userMapper.toUserDtos(users);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    //TODO: add authorization
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        UserDto userDto = userMapper.toUserDTO(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
