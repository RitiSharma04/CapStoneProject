package com.natwest.UserService.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigInteger;

@Entity
@Table(name="Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true)
    @NotNull
    private String email;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "First name must contain only letters and have at least 2 characters")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Last name must contain only letters and have at least 2 characters")
    private String lastName;
    @NotNull
    @Min(value = 7000000000L, message = "Phone number must be a valid 10-digit number starting from 7")
    @Max(value = 9999999999L, message = "Phone number must be a valid 10-digit number starting from 7")
    private BigInteger phoneNumber;


    @Size(min = 6, message = "Username must have at least 6 characters")
    private String username;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must have at least 8 characters, one uppercase, one lowercase, one digit, and one special character")
    private String password;
}
