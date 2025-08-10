package com.devtiro.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
