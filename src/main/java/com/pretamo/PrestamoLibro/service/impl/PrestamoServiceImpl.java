package com.pretamo.PrestamoLibro.service.impl;

import com.pretamo.PrestamoLibro.exception.BusinessException;
import com.pretamo.PrestamoLibro.exception.ResourceNotFoundException;
import com.pretamo.PrestamoLibro.model.EstadoPrestamo;
import com.pretamo.PrestamoLibro.model.Libro;
import com.pretamo.PrestamoLibro.model.Prestamo;
import com.pretamo.PrestamoLibro.model.Usuario;
import com.pretamo.PrestamoLibro.repository.PrestamoRepository;
import com.pretamo.PrestamoLibro.service.LibroService;
import com.pretamo.PrestamoLibro.service.PrestamoService;
import com.pretamo.PrestamoLibro.service.UsuarioService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioService usuarioService;
    private final LibroService libroService;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository,
            UsuarioService usuarioService,
            LibroService libroService) {
        this.prestamoRepository = prestamoRepository;
        this.usuarioService = usuarioService;
        this.libroService = libroService;
    }

    @Override
    public Prestamo prestarLibro(Long usuarioId, Long libroId) {
        Usuario usuario = usuarioService.obtenerPorId(usuarioId);
        Libro libro = libroService.obtenerPorId(libroId);

        if (libro.getStock() <= 0) {
            throw new BusinessException("El libro no tiene stock disponible");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setEstado(EstadoPrestamo.PRESTADO);

        libroService.disminuirStock(libro.getId());
        return prestamoRepository.save(prestamo);
    }

    @Override
    public Prestamo devolverLibro(Long prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new ResourceNotFoundException("Préstamo no encontrado con id: " + prestamoId));

        if (prestamo.getEstado() == EstadoPrestamo.DEVUELTO) {
            throw new BusinessException("El préstamo ya fue devuelto");
        }

        prestamo.setEstado(EstadoPrestamo.DEVUELTO);
        prestamo.setFechaDevolucion(LocalDate.now());
        libroService.aumentarStock(prestamo.getLibro().getId());
        return prestamoRepository.save(prestamo);
    }

    @Override
    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    @Override
    public List<Prestamo> obtenerPorUsuario(Long usuarioId) {
        usuarioService.obtenerPorId(usuarioId);
        return prestamoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Prestamo> obtenerPorLibro(Long libroId) {
        libroService.obtenerPorId(libroId);
        return prestamoRepository.findByLibroId(libroId);
    }
}