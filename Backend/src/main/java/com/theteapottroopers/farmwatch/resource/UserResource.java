package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.dto.UserLeanDto;
import com.theteapottroopers.farmwatch.dto.UserDto;
import com.theteapottroopers.farmwatch.exception.LastAdminDeletionException;
import com.theteapottroopers.farmwatch.mapper.UserMapper;
import com.theteapottroopers.farmwatch.exception.ErrorResponse;
import com.theteapottroopers.farmwatch.security.user.Role;
import com.theteapottroopers.farmwatch.security.user.User;
import com.theteapottroopers.farmwatch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'CARETAKER','ADMIN')")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof User){
            Long currentUserId = ((User) principal).getId();
            if(currentUserId != id && ((User) principal).getRole() != Role.ROLE_ADMIN){
                return new ResponseEntity<>( HttpStatus.FORBIDDEN);
            }
        }
        User user = userService.findUserById(id);
        UserDto userDto = userMapper.toUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDTO){
        try {
            User userToUpdate = userService.updateUser(userDTO);
            UserDto updateUserDto = userMapper.toUserDto(userToUpdate);
            return new ResponseEntity<>(updateUserDto,HttpStatus.OK);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
        }
    }

    @GetMapping("/caretakers")
    public ResponseEntity<List<UserLeanDto>> getAllCaretakers(){
        List<User> users = userService.findAllCaretakers();
        List<UserLeanDto> caretakerLeanDtos = userMapper.toCaretakerLeanDtos(users);
        return new ResponseEntity<>(caretakerLeanDtos, HttpStatus.OK);
    }
}
