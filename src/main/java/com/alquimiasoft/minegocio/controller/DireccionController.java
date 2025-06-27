package com.alquimiasoft.minegocio.controller;

import com.alquimiasoft.minegocio.dto.DireccionDTO;
import com.alquimiasoft.minegocio.entity.Direccion;
import com.alquimiasoft.minegocio.service.DireccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    // Registrar nueva dirección
    @PostMapping("/{clienteId}")
    public ResponseEntity<Direccion> registrarDireccion(
            @PathVariable Long clienteId,
            @Validated @RequestBody DireccionDTO direccionDTO) {
        Direccion creada = direccionService.registrarDireccion(clienteId, direccionDTO);
        return ResponseEntity.ok(creada);
    }

    // Listar direcciones por cliente
    @GetMapping("/{clienteId}")
    public ResponseEntity<List<Direccion>> listarDirecciones(@PathVariable Long clienteId) {
        List<Direccion> direcciones = direccionService.listarDireccionesPorCliente(clienteId);
        return ResponseEntity.ok(direcciones);
    }
}
