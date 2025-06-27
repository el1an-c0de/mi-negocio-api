package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.dto.ClienteDTO;
import com.alquimiasoft.minegocio.dto.DireccionDTO;
import com.alquimiasoft.minegocio.entity.Cliente;
import com.alquimiasoft.minegocio.entity.TipoIdentificacion;
import com.alquimiasoft.minegocio.repository.ClienteRepository;
import com.alquimiasoft.minegocio.repository.DireccionRepository;
import com.alquimiasoft.minegocio.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    private ClienteRepository clienteRepository;
    private DireccionRepository direccionRepository;
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        clienteRepository = mock(ClienteRepository.class);
        direccionRepository = mock(DireccionRepository.class);
        clienteService = new ClienteServiceImpl(clienteRepository, direccionRepository);
    }

    @Test
    void crearCliente_deberiaGuardarClienteYDireccionMatriz() {
        // Arrange
        ClienteDTO dto = new ClienteDTO();
        dto.setTipoIdentificacion(TipoIdentificacion.CEDULA);
        dto.setNumeroIdentificacion("1234567890");
        dto.setNombres("Juan Pérez");
        dto.setCorreo("juan@example.com");
        dto.setNumeroCelular("0999999999");

        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setProvincia("Pichincha");
        direccionDTO.setCiudad("Quito");
        direccionDTO.setDireccion("Av. Amazonas");
        direccionDTO.setEsMatriz(true);

        dto.setDireccionMatriz(direccionDTO);

        when(clienteRepository.existsByNumeroIdentificacion("1234567890")).thenReturn(false);

        ArgumentCaptor<Cliente> captor = ArgumentCaptor.forClass(Cliente.class);

        // Act
        clienteService.crearCliente(dto);

        // Assert
        verify(clienteRepository).save(captor.capture());
        Cliente clienteGuardado = captor.getValue();
        assertThat(clienteGuardado.getNumeroIdentificacion()).isEqualTo("1234567890");
        assertThat(clienteGuardado.getDirecciones()).hasSize(1);
        assertThat(clienteGuardado.getDireccionMatriz()).isNotNull();
        assertThat(clienteGuardado.getDireccionMatriz().isEsMatriz()).isTrue();
    }

    @Test
    void crearCliente_siIdentificacionExiste_deberiaLanzarExcepcion() {
        // Arrange
        ClienteDTO dto = new ClienteDTO();
        dto.setTipoIdentificacion(TipoIdentificacion.CEDULA);
        dto.setNumeroIdentificacion("1234567890");
        dto.setNombres("Juan Pérez");

        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setProvincia("Pichincha");
        direccionDTO.setCiudad("Quito");
        direccionDTO.setDireccion("Av. Amazonas");
        direccionDTO.setEsMatriz(true);

        dto.setDireccionMatriz(direccionDTO);

        when(clienteRepository.existsByNumeroIdentificacion("1234567890")).thenReturn(true);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clienteService.crearCliente(dto));
    }

    @Test
    void editarCliente_deberiaActualizarDatosCliente() {
        // Arrange
        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(1L);
        clienteExistente.setNumeroIdentificacion("1234567890");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.findByNumeroIdentificacion("1234567890")).thenReturn(Optional.of(clienteExistente));

        ClienteDTO dto = new ClienteDTO();
        dto.setTipoIdentificacion(TipoIdentificacion.CEDULA);
        dto.setNumeroIdentificacion("1234567890");
        dto.setNombres("Actualizado");
        dto.setCorreo("actualizado@example.com");
        dto.setNumeroCelular("0999999999");

        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setProvincia("Pichincha");
        direccionDTO.setCiudad("Quito");
        direccionDTO.setDireccion("Av. Amazonas");
        direccionDTO.setEsMatriz(true);
        dto.setDireccionMatriz(direccionDTO);

        // Act
        clienteService.editarCliente(1L, dto);

        // Assert
        verify(clienteRepository).save(clienteExistente);
        assertThat(clienteExistente.getNombres()).isEqualTo("Actualizado");
        assertThat(clienteExistente.getCorreo()).isEqualTo("actualizado@example.com");
    }
}
