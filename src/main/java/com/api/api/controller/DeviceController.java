package com.api.api.controller;

import com.api.api.dto.UpdateDeviceRequest;
import com.api.api.model.Device;
import com.api.api.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/devices")
@CrossOrigin(origins = "*")
@Tag(name = "Dispositivos", description = "API para gestión del catálogo de dispositivos móviles")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Operation(summary = "Listar dispositivos",
               description = "Retorna la lista completa de dispositivos del catálogo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de dispositivos obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<Device>> listarDispositivos() {
        return ResponseEntity.ok(deviceService.obtenerTodos());
    }

    @Operation(summary = "Obtener dispositivo por ID",
               description = "Retorna el detalle de un dispositivo específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dispositivo encontrado"),
        @ApiResponse(responseCode = "404", description = "Dispositivo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Device> obtenerDispositivo(
            @Parameter(description = "ID del dispositivo")
            @PathVariable UUID id) {
        return deviceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar dispositivo",
               description = "Actualiza parcialmente un dispositivo. Solo se actualizarán los campos enviados en el request. Campos soportados: precio, imageUrl.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dispositivo actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Request inválido - no hay campos para actualizar"),
        @ApiResponse(responseCode = "404", description = "Dispositivo no encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Device> updateDevice(
            @Parameter(description = "ID del dispositivo")
            @PathVariable UUID id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Campos a actualizar. Solo se modificarán los campos presentes en el request."
            )
            @RequestBody UpdateDeviceRequest request) {

        // Validar que al menos venga algún campo actualizable
        if (request.getPrice() == null) {
            return ResponseEntity.badRequest().build();
        }

        return deviceService.updateDevice(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar dispositivo",
               description = "Elimina un dispositivo del catálogo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Dispositivo eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Dispositivo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDispositivo(
            @Parameter(description = "ID del dispositivo a eliminar")
            @PathVariable UUID id) {
        if (deviceService.remove(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear nuevo dispositivo",
               description = "Crea un nuevo dispositivo en el catálogo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Dispositivo creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<Device> crearDispositivo(
            @Parameter(description = "Datos del nuevo dispositivo")
            @RequestBody Device device) {
        Device nuevoDispositivo = deviceService.create(device);
        return ResponseEntity.status(201).body(nuevoDispositivo);
    }
}
