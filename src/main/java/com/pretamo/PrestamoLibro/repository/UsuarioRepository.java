package com.pretamo.PrestamoLibro.repository;

import com.pretamo.PrestamoLibro.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}