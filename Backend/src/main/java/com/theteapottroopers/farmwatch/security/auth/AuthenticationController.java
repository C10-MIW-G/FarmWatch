package com.theteapottroopers.farmwatch.security.auth;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
    private final String captchaKey = "6LeKLl0kAAAAANJ5-3s9PLySY-r_Ln13epzRtN3d";

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        // TODO check captcha token.
        HttpClient client = HttpClient.newHttpClient();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append()
        HttpRequest captchaCheck = HttpRequest.newBuilder()
                .uri(URI.create("https://www.google.com/recaptcha/api/siteverify"))
                .POST(HttpRequest.BodyPublishers.ofString())
                .build();
        try {
            HttpResponse<String> response = client.send(captchaCheck, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
