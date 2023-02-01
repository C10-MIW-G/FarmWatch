package com.theteapottroopers.farmwatch.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Wat doet deze klasse?
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
}
