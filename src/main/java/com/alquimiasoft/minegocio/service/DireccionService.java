package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.dto.DireccionDTO;
import com.alquimiasoft.minegocio.entity.Direccion;

import java.util.List;

public interface DireccionService {

    Direccion registrarDireccion(Long clienteId, DireccionDTO direccionDTO);

    List<Direccion> listarDireccionesPorCliente(Long clienteId);
}
