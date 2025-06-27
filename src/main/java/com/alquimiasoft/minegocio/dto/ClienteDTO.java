package com.alquimiasoft.minegocio.dto;

import com.alquimiasoft.minegocio.entity.TipoIdentificacion;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteDTO {

    @NotNull
    private TipoIdentificacion tipoIdentificacion;

    @NotBlank
    private String numeroIdentificacion;

    @NotBlank
    private String nombres;

    @Email
    private String correo;

    private String numeroCelular;

    @NotNull
    private DireccionDTO direccionMatriz;

    // Getters y Setters
    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public DireccionDTO getDireccionMatriz() {
        return direccionMatriz;
    }

    public void setDireccionMatriz(DireccionDTO direccionMatriz) {
        this.direccionMatriz = direccionMatriz;
    }
}
