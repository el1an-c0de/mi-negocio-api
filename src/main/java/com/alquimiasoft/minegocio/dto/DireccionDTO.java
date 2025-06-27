package com.alquimiasoft.minegocio.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DireccionDTO {

    @NotBlank
    private String provincia;

    @NotBlank
    private String ciudad;

    @NotBlank
    private String direccion;

    @NotNull
    private Boolean esMatriz;

    // Getters y Setters
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean isEsMatriz() {
        return esMatriz;
    }

    public void setEsMatriz(Boolean esMatriz) {
        this.esMatriz = esMatriz;
    }
}
