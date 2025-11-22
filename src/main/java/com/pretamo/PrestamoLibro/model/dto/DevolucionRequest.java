package com.pretamo.PrestamoLibro.model.dto;

import jakarta.validation.constraints.NotNull;

public class DevolucionRequest {

    @NotNull
    private Long prestamoId;

    public Long getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(Long prestamoId) {
        this.prestamoId = prestamoId;
    }
}