package com.sushant.ordermanagement.common.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        int status,
        String error,
        String message,
        String path,
        Map<String, String> validationErrors,
        LocalDateTime timestamp
) {
}