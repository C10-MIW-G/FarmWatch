package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.dto.AnimalDto;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.security.user.User;
import com.theteapottroopers.farmwatch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Wat doet deze klasse?
 */
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class UserResource {

    private final UserService userService;


    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
