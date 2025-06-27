package com.alquimiasoft.minegocio.repository;

import com.alquimiasoft.minegocio.entity.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    List<Direccion> findByClienteId(Long clienteId);
    boolean existsByClienteIdAndEsMatrizTrue(Long clienteId);
}
