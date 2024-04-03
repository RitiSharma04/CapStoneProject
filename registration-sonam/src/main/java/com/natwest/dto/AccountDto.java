package com.natwest.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDto{
    private int id;
    private int holderId;
    private String accountNumber;
    private String accountType;
    private String ifscCode;
    private String branch;

}
