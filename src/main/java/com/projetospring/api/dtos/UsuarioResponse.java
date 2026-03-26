package com.projetospring.api.dtos;

import java.time.OffsetDateTime;

import com.projetospring.api.entities.UserRole;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private UserRole role;
    private OffsetDateTime criadoEm;
}
