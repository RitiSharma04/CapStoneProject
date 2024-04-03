package com.natwest.capstone.transactionservice.controlleradvice;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorInfo {
    private String errorMsg;
    private HttpStatus status;
    private LocalDateTime time;
}
