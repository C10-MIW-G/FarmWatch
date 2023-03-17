package com.theteapottroopers.farmwatch.dto;

import com.theteapottroopers.farmwatch.security.user.Role;
import lombok.*;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * DTO that contains all relevant information from user.
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {

    private Long id;
    private String fullName;
    private String email;
    private String username;
    private Role role;

}
