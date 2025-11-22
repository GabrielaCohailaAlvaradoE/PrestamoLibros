package com.pretamo.PrestamoLibro.model.dto;

import jakarta.validation.constraints.NotNull;

public class PrestamoRequest {

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long libroId;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }
}