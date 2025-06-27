package com.alquimiasoft.minegocio.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_identificacion", nullable = false)
    private TipoIdentificacion tipoIdentificacion;

    @NotBlank
    @Column(name = "numero_identificacion", nullable = false, unique = true, length = 20)
    private String numeroIdentificacion;

    @NotBlank
    @Column(name = "nombres", nullable = false, length = 200)
    private String nombres;

    @Email
    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "numero_celular", length = 15)
    private String numeroCelular;

    // Relación uno a muchos con Direccion
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Direccion> direcciones = new ArrayList<>();

    // Constructores
    public Cliente() {}

    public Cliente(TipoIdentificacion tipoIdentificacion, String numeroIdentificacion,
                   String nombres, String correo, String numeroCelular) {
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombres = nombres;
        this.correo = correo;
        this.numeroCelular = numeroCelular;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    // Método de utilidad para agregar una dirección
    public void agregarDireccion(Direccion direccion) {
        direcciones.add(direccion);
        direccion.setCliente(this);
    }

    // Método para obtener la dirección matriz
    public Direccion getDireccionMatriz() {
        return direcciones.stream()
                .filter(Direccion::isEsMatriz)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", tipoIdentificacion=" + tipoIdentificacion +
                ", numeroIdentificacion='" + numeroIdentificacion + '\'' +
                ", nombres='" + nombres + '\'' +
                ", correo='" + correo + '\'' +
                ", numeroCelular='" + numeroCelular + '\'' +
                '}';
    }
}