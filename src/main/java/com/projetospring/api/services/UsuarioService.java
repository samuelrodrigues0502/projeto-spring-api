package com.projetospring.api.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetospring.api.dtos.UsuarioCreateRequest;
import com.projetospring.api.dtos.UsuarioResponse;
import com.projetospring.api.entities.UserRole;
import com.projetospring.api.entities.Usuario;
import com.projetospring.api.mappers.UsuarioMapper;
import com.projetospring.api.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toResponse)
                .toList();
    }

    @Transactional
    public UsuarioResponse criar(UsuarioCreateRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Ja existe usuario com esse email.");
        }

        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senhaHash(passwordEncoder.encode(request.getSenha()))
                .role(UserRole.USER)
                .build();

        return UsuarioMapper.toResponse(usuarioRepository.save(usuario));
    }
}
