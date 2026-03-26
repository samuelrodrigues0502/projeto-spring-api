package com.projetospring.api.mappers;

import com.projetospring.api.dtos.UsuarioResponse;
import com.projetospring.api.entities.Usuario;

public final class UsuarioMapper {

    private UsuarioMapper() {
    }

    public static UsuarioResponse toResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .role(usuario.getRole())
                .criadoEm(usuario.getCriadoEm())
                .build();
    }
}
