package com.pretamo.PrestamoLibro.service;

import com.pretamo.PrestamoLibro.model.Libro;
import java.util.List;

public interface LibroService {

    List<Libro> obtenerTodos();

    Libro obtenerPorId(Long id);

    Libro crear(Libro libro);

    Libro actualizar(Long id, Libro libro);

    void eliminar(Long id);

    Libro disminuirStock(Long id);

    Libro aumentarStock(Long id);
}