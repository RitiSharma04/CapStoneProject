package com.natwest.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private int id;
    private String emailId;
    private String name;
    private String address;
    private String mobileNumber;
    private String branch;
}
