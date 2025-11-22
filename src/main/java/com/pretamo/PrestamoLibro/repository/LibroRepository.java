package com.pretamo.PrestamoLibro.repository;

import com.pretamo.PrestamoLibro.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}