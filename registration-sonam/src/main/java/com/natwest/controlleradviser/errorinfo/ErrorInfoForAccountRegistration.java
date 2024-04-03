package com.natwest.controlleradviser.errorinfo;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfoForAccountRegistration {
    private String errorMessage;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;
}
