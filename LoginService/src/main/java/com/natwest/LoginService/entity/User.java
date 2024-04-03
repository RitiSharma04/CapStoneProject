package com.natwest.LoginService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name="Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true)

    private String email;

    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "First name must contain only letters and have at least 2 characters")
    private String firstName;


    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Last name must contain only letters and have at least 2 characters")
    private String lastName;
    @Min(value = 7000000000L, message = "Phone number must be a valid 10-digit number starting from 7")
    @Max(value = 9999999999L, message = "Phone number must be a valid 10-digit number starting from 7")
    private BigInteger phoneNumber;


    @Size(min = 6, message = "Username must have at least 6 characters")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must have at least 8 characters, one uppercase, one lowercase, one digit, and one special character")
    private String password;


    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
        return true;
    }
}

