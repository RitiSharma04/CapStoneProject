package com.natwest.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="User_Ragistration_Table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String emailId;
    private String name;
    private String address;
    private String accountNumber;
    private String mobileNumber;
    private String password;
}
