package com.alquimiasoft.minegocio.controller;

import com.alquimiasoft.minegocio.dto.ClienteDTO;
import com.alquimiasoft.minegocio.entity.Cliente;
import com.alquimiasoft.minegocio.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Crear cliente
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Validated @RequestBody ClienteDTO clienteDTO) {
        Cliente creado = clienteService.crearCliente(clienteDTO);
        return ResponseEntity.ok(creado);
    }

    // Editar cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarCliente(
            @PathVariable Long id,
            @Validated @RequestBody ClienteDTO clienteDTO) {
        Cliente actualizado = clienteService.editarCliente(id, clienteDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar clientes por filtro
    @GetMapping("/buscar")
    public ResponseEntity<List<Cliente>> buscarClientes(@RequestParam String filtro) {
        List<Cliente> resultados = clienteService.buscarClientes(filtro);
        return ResponseEntity.ok(resultados);
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
        return clienteService.obtenerClientePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
