package com.alquimiasoft.minegocio.entity;

public enum TipoIdentificacion {
    CEDULA("Cédula"),
    RUC("RUC");

    private final String descripcion;

    TipoIdentificacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}