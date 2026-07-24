package com.example.projecturl.urlshortener.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public class UpdateUrlRequest {
    @Pattern(
            regexp = "^(https?://).+",
            message = "URL must start with http:// or https://"
    )
    private String originalUrl;
    @Future(message = "Expiry date must be in the future")
    private LocalDateTime expiresAt;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

}