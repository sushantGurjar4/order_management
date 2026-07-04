package com.sushant.ordermanagement.health.api;

import com.sushant.ordermanagement.common.response.ApiResponse;
import com.sushant.ordermanagement.common.util.ApiResponseBuilder;
import com.sushant.ordermanagement.health.dto.HealthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
@Tag(name = "Health", description = "Health check APIs")
public class HealthController {

    @GetMapping
    @Operation(summary = "Get application health status")
    public ResponseEntity<ApiResponse<HealthResponse>> health() {
        HealthResponse response = new HealthResponse(
                "ordermanagement",
                "UP",
                "v1"
        );

        return ResponseEntity.ok(
                ApiResponseBuilder.success("Application is running", response)
        );
    }
}