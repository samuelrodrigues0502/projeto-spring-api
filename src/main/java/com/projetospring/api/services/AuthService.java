package com.projetospring.api.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.projetospring.api.dtos.AuthLoginRequest;
import com.projetospring.api.dtos.AuthResponse;
import com.projetospring.api.entities.Usuario;
import com.projetospring.api.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public AuthResponse login(AuthLoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha()));

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas."));

        return AuthResponse.builder()
                .token(jwtService.generateToken(usuario))
                .tipo("Bearer")
                .expiresInSeconds(jwtService.getExpirationSeconds())
                .build();
    }
}
