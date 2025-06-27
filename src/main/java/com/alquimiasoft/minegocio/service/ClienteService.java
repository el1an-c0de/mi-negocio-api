package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.dto.ClienteDTO;
import com.alquimiasoft.minegocio.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente crearCliente(ClienteDTO clienteDTO);

    Cliente editarCliente(Long clienteId, ClienteDTO clienteDTO);

    void eliminarCliente(Long clienteId);

    List<Cliente> buscarClientes(String filtro);

    Optional<Cliente> obtenerClientePorId(Long clienteId);
}
