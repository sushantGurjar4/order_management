package com.sushant.ordermanagement.auth.api;

import com.sushant.ordermanagement.auth.application.OAuth2CustomerService;
import com.sushant.ordermanagement.auth.dto.AuthenticatedUserResponse;
import com.sushant.ordermanagement.common.response.ApiResponse;
import com.sushant.ordermanagement.common.util.ApiResponseBuilder;
import com.sushant.ordermanagement.customer.domain.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Authentication APIs")
public class AuthController {

    private final OAuth2CustomerService oauth2CustomerService;

    public AuthController(OAuth2CustomerService oauth2CustomerService) {
        this.oauth2CustomerService = oauth2CustomerService;
    }

    @GetMapping("/login/google")
    @Operation(summary = "Google login entry information")
    public ResponseEntity<ApiResponse<String>> googleLoginInfo() {
        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        "Open /oauth2/authorization/google in browser to login with Google",
                        "/oauth2/authorization/google"
                )
        );
    }

    @GetMapping("/me")
    @Operation(summary = "Get authenticated user details")
    public ResponseEntity<ApiResponse<AuthenticatedUserResponse>> getCurrentUser(
            @AuthenticationPrincipal OAuth2User oauth2User
    ) {
        Customer customer = oauth2CustomerService.syncCustomer(oauth2User);

        AuthenticatedUserResponse response = new AuthenticatedUserResponse(
                customer.getName(),
                customer.getEmail(),
                "google"
        );

        return ResponseEntity.ok(
                ApiResponseBuilder.success("Authenticated user fetched successfully", response)
        );
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout current user")
    public ResponseEntity<ApiResponse<String>> logoutInfo() {
        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        "Call /logout from browser or use Spring Security logout flow",
                        "Logout endpoint available at /logout"
                )
        );
    }
}