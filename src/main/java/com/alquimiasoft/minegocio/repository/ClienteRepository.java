package com.alquimiasoft.minegocio.repository;

import com.alquimiasoft.minegocio.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByNumeroIdentificacion(String numeroIdentificacion);
    List<Cliente> findByNumeroIdentificacionContainingIgnoreCaseOrNombresContainingIgnoreCase(String numeroIdentificacion, String nombres);
    boolean existsByNumeroIdentificacion(String numeroIdentificacion);
}
