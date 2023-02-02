package com.theteapottroopers.farmwatch.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Authentication Response message
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse{
    private String token;
    private String username;
    private String email;
    private String role;
}