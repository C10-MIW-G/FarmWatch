package com.theteapottroopers.farmwatch.security.auth;

import com.theteapottroopers.farmwatch.exception.UserNotFoundException;
import com.theteapottroopers.farmwatch.repository.ForgotPasswordRepository;
import com.theteapottroopers.farmwatch.security.event.OnForgotPasswordEvent;
import com.theteapottroopers.farmwatch.security.event.OnRegistrationCompleteEvent;
import com.theteapottroopers.farmwatch.security.user.User;
import com.theteapottroopers.farmwatch.service.UserService;
import lombok.RequiredArgsConstructor;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;


/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Has access to the authentication pages
 */
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;
    private final CaptchaChecker captchaChecker;
    private final ForgotPasswordRepository forgotPasswordRepository;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        if (!captchaChecker.verify(request.getCaptchaToken())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/registrationConfirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam("token") String token){
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if(verificationToken == null){
            return ResponseEntity.status(498).body("Invalid token");
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0){
            userService.removeVerificationToken(verificationToken);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));
            return ResponseEntity.status(498).body("Token has expired, please check your email");
        }

        if(user.isEnabled()){
            return ResponseEntity.status(200).body("Account has already been activated");
        }

        user.setVerified(true);
        userService.saveUser(user);
        userService.removeVerificationToken(verificationToken);
        return ResponseEntity.status(200).body("Account activated");
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request){
        User user = userService.findUserByEmail(request.getEmail());
        if(user != null){
            eventPublisher.publishEvent(new OnForgotPasswordEvent(user));
        }else{
            throw new UserNotFoundException("User could not be found");
        }
        return ResponseEntity.status(200).body("Password reset mail has been sent");
    }

    @PatchMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request){
        ForgotPasswordToken forgotPasswordToken = forgotPasswordRepository.findByToken(request.getToken()).orElse(null);
        if(forgotPasswordToken != null){
            User user = forgotPasswordToken.getUser();
            userService.changePassword(user, request.getPassword());
            userService.removeForgotPasswordToken(forgotPasswordToken);
        }else{
            return ResponseEntity.status(409).body("Token is not valid");
        }
        return ResponseEntity.status(200).body("Password has been changed");
    }
}
