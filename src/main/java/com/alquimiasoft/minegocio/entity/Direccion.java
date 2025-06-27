package com.alquimiasoft.minegocio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "direcciones")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "provincia", nullable = false, length = 100)
    private String provincia;

    @NotBlank
    @Column(name = "ciudad", nullable = false, length = 100)
    private String ciudad;

    @NotBlank
    @Column(name = "direccion", nullable = false, length = 500)
    private String direccion;

    @NotNull
    @Column(name = "es_matriz", nullable = false)
    private boolean esMatriz = false;

    // Relación muchos a uno con Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnore  // Evita problemas de serialización circular
    private Cliente cliente;

    // Constructores
    public Direccion() {}

    public Direccion(String provincia, String ciudad, String direccion, boolean esMatriz) {
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.esMatriz = esMatriz;
    }

    public Direccion(String provincia, String ciudad, String direccion, boolean esMatriz, Cliente cliente) {
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.esMatriz = esMatriz;
        this.cliente = cliente;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isEsMatriz() {
        return esMatriz;
    }

    public void setEsMatriz(boolean esMatriz) {
        this.esMatriz = esMatriz;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "id=" + id +
                ", provincia='" + provincia + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", direccion='" + direccion + '\'' +
                ", esMatriz=" + esMatriz +
                '}';
    }
}