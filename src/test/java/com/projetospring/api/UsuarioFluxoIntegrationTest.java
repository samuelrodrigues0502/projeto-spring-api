package com.projetospring.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projetospring.api.dtos.AuthLoginRequest;
import com.projetospring.api.dtos.AuthResponse;
import com.projetospring.api.dtos.UsuarioCreateRequest;
import com.projetospring.api.services.AuthService;
import com.projetospring.api.services.UsuarioService;

@SpringBootTest
class UsuarioFluxoIntegrationTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthService authService;

    @Test
    void deveCadastrarLogarEAcessarRotaProtegida() throws Exception {
        String email = "usuario" + System.currentTimeMillis() + "@email.com";

        UsuarioCreateRequest createRequest = new UsuarioCreateRequest();
        createRequest.setNome("Samuel");
        createRequest.setEmail(email);
        createRequest.setSenha("12345678");

        usuarioService.criar(createRequest);

        AuthLoginRequest loginRequest = new AuthLoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setSenha("12345678");

        AuthResponse response = authService.login(loginRequest);

        assertNotNull(response);
        assertNotNull(response.getToken());
        assertFalse(response.getToken().isBlank());
        assertFalse(usuarioService.listarTodos().isEmpty());
    }
}
