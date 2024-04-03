package com.natwest.model;

import com.natwest.dto.AccountDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "holder_id")
    private int holderId;
    private String accountNumber;
    private String accountType;
    @Column(name = "IFSC_Code")
    private String ifscCode;
    private String branch;
    private double balance;

}
