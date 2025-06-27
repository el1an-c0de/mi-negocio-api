package com.alquimiasoft.minegocio.service.impl;

import com.alquimiasoft.minegocio.dto.DireccionDTO;
import com.alquimiasoft.minegocio.entity.Cliente;
import com.alquimiasoft.minegocio.entity.Direccion;
import com.alquimiasoft.minegocio.repository.ClienteRepository;
import com.alquimiasoft.minegocio.repository.DireccionRepository;
import com.alquimiasoft.minegocio.service.DireccionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DireccionServiceImpl implements DireccionService {

    private final ClienteRepository clienteRepository;
    private final DireccionRepository direccionRepository;

    public DireccionServiceImpl(ClienteRepository clienteRepository, DireccionRepository direccionRepository) {
        this.clienteRepository = clienteRepository;
        this.direccionRepository = direccionRepository;
    }

    @Override
    @Transactional
    public Direccion registrarDireccion(Long clienteId, DireccionDTO direccionDTO) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado."));

        // Si la nueva dirección es matriz, validar que no exista otra matriz
        if (direccionDTO.isEsMatriz()) {
            boolean existeMatriz = direccionRepository.existsByClienteIdAndEsMatrizTrue(clienteId);
            if (existeMatriz) {
                throw new IllegalArgumentException("El cliente ya tiene una dirección matriz registrada.");
            }
        }

        Direccion direccion = new Direccion(
                direccionDTO.getProvincia(),
                direccionDTO.getCiudad(),
                direccionDTO.getDireccion(),
                direccionDTO.isEsMatriz()
        );

        direccion.setCliente(cliente);

        return direccionRepository.save(direccion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Direccion> listarDireccionesPorCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new IllegalArgumentException("Cliente no encontrado.");
        }
        return direccionRepository.findByClienteId(clienteId);
    }
}
