package com.sushant.ordermanagement.health.dto;

public record HealthResponse(
        String application,
        String status,
        String version
) {
}