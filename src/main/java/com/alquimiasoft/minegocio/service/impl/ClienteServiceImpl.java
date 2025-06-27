package com.alquimiasoft.minegocio.service.impl;

import com.alquimiasoft.minegocio.dto.ClienteDTO;
import com.alquimiasoft.minegocio.entity.Cliente;
import com.alquimiasoft.minegocio.entity.Direccion;
import com.alquimiasoft.minegocio.repository.ClienteRepository;
import com.alquimiasoft.minegocio.repository.DireccionRepository;
import com.alquimiasoft.minegocio.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final DireccionRepository direccionRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository, DireccionRepository direccionRepository) {
        this.clienteRepository = clienteRepository;
        this.direccionRepository = direccionRepository;
    }

    @Override
    @Transactional
    public Cliente crearCliente(ClienteDTO clienteDTO) {
        // Validar que no exista el cliente con la misma identificación
        if (clienteRepository.existsByNumeroIdentificacion(clienteDTO.getNumeroIdentificacion())) {
            throw new IllegalArgumentException("Ya existe un cliente con el mismo número de identificación.");
        }

        // Crear entidad Cliente
        Cliente cliente = new Cliente(
                clienteDTO.getTipoIdentificacion(),
                clienteDTO.getNumeroIdentificacion(),
                clienteDTO.getNombres(),
                clienteDTO.getCorreo(),
                clienteDTO.getNumeroCelular()
        );

        // Crear dirección matriz
        Direccion direccionMatriz = new Direccion(
                clienteDTO.getDireccionMatriz().getProvincia(),
                clienteDTO.getDireccionMatriz().getCiudad(),
                clienteDTO.getDireccionMatriz().getDireccion(),
                true
        );

        cliente.agregarDireccion(direccionMatriz);

        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Cliente editarCliente(Long clienteId, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado."));

        // Validar que no exista otro cliente con el mismo número de identificación
        Optional<Cliente> existente = clienteRepository.findByNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
        if (existente.isPresent() && !existente.get().getId().equals(clienteId)) {
            throw new IllegalArgumentException("Ya existe otro cliente con el mismo número de identificación.");
        }

        cliente.setTipoIdentificacion(clienteDTO.getTipoIdentificacion());
        cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
        cliente.setNombres(clienteDTO.getNombres());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setNumeroCelular(clienteDTO.getNumeroCelular());

        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void eliminarCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new IllegalArgumentException("Cliente no encontrado.");
        }
        clienteRepository.deleteById(clienteId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> buscarClientes(String filtro) {
        return clienteRepository.findByNumeroIdentificacionContainingIgnoreCaseOrNombresContainingIgnoreCase(filtro, filtro);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> obtenerClientePorId(Long clienteId) {
        return clienteRepository.findById(clienteId);
    }
}
