package com.theteapottroopers.farmwatch.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Register request message
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String captchaToken;
}
