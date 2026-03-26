package com.projetospring.api.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String token;
    private String tipo;
    private long expiresInSeconds;
}
