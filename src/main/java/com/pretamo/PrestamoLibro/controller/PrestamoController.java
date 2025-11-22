package com.pretamo.PrestamoLibro.controller;

import com.pretamo.PrestamoLibro.model.Prestamo;
import com.pretamo.PrestamoLibro.model.dto.DevolucionRequest;
import com.pretamo.PrestamoLibro.model.dto.PrestamoRequest;
import com.pretamo.PrestamoLibro.service.PrestamoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping("/prestar")
    public ResponseEntity<Prestamo> prestarLibro(@Valid @RequestBody PrestamoRequest request) {
        Prestamo prestamo = prestamoService.prestarLibro(request.getUsuarioId(), request.getLibroId());
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamo);
    }

    @PostMapping("/devolver")
    public ResponseEntity<Prestamo> devolverLibro(@Valid @RequestBody DevolucionRequest request) {
        Prestamo prestamo = prestamoService.devolverLibro(request.getPrestamoId());
        return ResponseEntity.ok(prestamo);
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> listarPrestamos() {
        return ResponseEntity.ok(prestamoService.obtenerTodos());
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Prestamo>> prestamosPorUsuario(@PathVariable("id") Long usuarioId) {
        return ResponseEntity.ok(prestamoService.obtenerPorUsuario(usuarioId));
    }

    @GetMapping("/libro/{id}")
    public ResponseEntity<List<Prestamo>> prestamosPorLibro(@PathVariable("id") Long libroId) {
        return ResponseEntity.ok(prestamoService.obtenerPorLibro(libroId));
    }
}