package com.bank.moneytransferservice.exceptions;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;


public  class ValidationErrorResponse extends ErrorResponse {

    public ValidationErrorResponse(int status, String message, LocalDateTime timestamp, Map<String, String> fieldErrors) {
            super(status, message, timestamp);
    }
    }