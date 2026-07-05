package com.sushant.ordermanagement.auth.dto;

public record AuthenticatedUserResponse(
        String name,
        String email,
        String provider
) {
}