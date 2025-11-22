package com.pretamo.PrestamoLibro.service;

import com.pretamo.PrestamoLibro.model.Prestamo;
import java.util.List;

public interface PrestamoService {

    Prestamo prestarLibro(Long usuarioId, Long libroId);

    Prestamo devolverLibro(Long prestamoId);

    List<Prestamo> obtenerTodos();

    List<Prestamo> obtenerPorUsuario(Long usuarioId);

    List<Prestamo> obtenerPorLibro(Long libroId);
}