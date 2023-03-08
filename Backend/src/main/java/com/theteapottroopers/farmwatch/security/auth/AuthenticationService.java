package com.theteapottroopers.farmwatch.security.auth;

import com.theteapottroopers.farmwatch.exception.LoginException;
import com.theteapottroopers.farmwatch.exception.SomethingWentWrongException;
import com.theteapottroopers.farmwatch.repository.UserRepository;
import com.theteapottroopers.farmwatch.security.config.JwtService;
import com.theteapottroopers.farmwatch.security.event.OnRegistrationCompleteEvent;
import com.theteapottroopers.farmwatch.security.user.Role;
import com.theteapottroopers.farmwatch.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Handles the register and login requests
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ApplicationEventPublisher eventPublisher;

    public RegisterResponse register(RegisterRequest request) {
        User user;
        try {
            user = User.builder()
                    .fullName(request.getFullName())
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ROLE_USER)
                    .open(false)
                    .build();
            userRepository.save(user);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));
        } catch (Exception exception) {
            throw new SomethingWentWrongException("Something went wrong. Try using a different username or email");
        }
        return RegisterResponse.builder()
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new LoginException("Something went wrong please try again"));
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole().toString().substring(5))
                .build();
    }
}
