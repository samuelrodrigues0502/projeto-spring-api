package com.projetospring.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetospring.api.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);
}
