package com.theteapottroopers.farmwatch.security.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * A user for the application
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"username","email"}))
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String fullName;
    @Email
    @NotNull
    @Column(name="email", nullable = false, unique = true)
    private String email;
    @NotNull
    @Column(name="username", nullable = false, unique = true)
    private String username;
    @NotNull
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @NotNull
    @Column(nullable = false)
    private boolean verified;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return verified;
    }
}
