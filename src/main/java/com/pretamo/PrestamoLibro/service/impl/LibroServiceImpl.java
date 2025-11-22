package com.pretamo.PrestamoLibro.service.impl;

import com.pretamo.PrestamoLibro.exception.BusinessException;
import com.pretamo.PrestamoLibro.exception.ResourceNotFoundException;
import com.pretamo.PrestamoLibro.model.Libro;
import com.pretamo.PrestamoLibro.repository.LibroRepository;
import com.pretamo.PrestamoLibro.service.LibroService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    @Override
    public Libro obtenerPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con id: " + id));
    }

    @Override
    public Libro crear(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro actualizar(Long id, Libro libro) {
        Libro existente = obtenerPorId(id);
        existente.setTitulo(libro.getTitulo());
        existente.setAutor(libro.getAutor());
        existente.setStock(libro.getStock());
        return libroRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        Libro existente = obtenerPorId(id);
        libroRepository.delete(existente);
    }

    @Override
    public Libro disminuirStock(Long id) {
        Libro libro = obtenerPorId(id);
        if (libro.getStock() <= 0) {
            throw new BusinessException("No hay stock disponible para el libro con id: " + id);
        }
        libro.setStock(libro.getStock() - 1);
        return libroRepository.save(libro);
    }

    @Override
    public Libro aumentarStock(Long id) {
        Libro libro = obtenerPorId(id);
        libro.setStock(libro.getStock() + 1);
        return libroRepository.save(libro);
    }
}