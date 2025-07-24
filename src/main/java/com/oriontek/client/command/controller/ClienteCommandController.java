package com.oriontek.client.command.controller;

import com.oriontek.client.command.commands.RemoveDireccionFromClienteCommand;
import com.oriontek.client.command.controller.responses.ClienteCreatedResponse;
import com.oriontek.client.command.controller.responses.DireccionCreatedResponse;
import com.oriontek.client.command.service.ClienteCommandService;
import com.oriontek.client.command.commands.CreateClienteCommand;
import com.oriontek.client.command.commands.CreateDireccionCommand;
import com.oriontek.client.command.commands.UpdateClienteCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes/commands")
@Tag(name = "Comandos de Clientes", description = "Endpoints para operaciones de escritura (crear, actualizar, eliminar) de Clientes y Direcciones.")
public class ClienteCommandController {
    private final ClienteCommandService clienteCommandService;
    public ClienteCommandController(ClienteCommandService clienteCommandService, ClienteCommandService clienteCommandService1) {
        this.clienteCommandService = clienteCommandService1;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente", description = "Permite registrar un nuevo cliente con sus datos básicos y direcciones iniciales.")
    @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente.")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida (problemas de validación).")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    public ResponseEntity<ClienteCreatedResponse> createCliente(@Valid @RequestBody CreateClienteCommand command) {
        var response = clienteCommandService.createCliente(command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/{clientId}/direcciones")
    @Operation(summary = "Agregar una dirección a un cliente existente", description = "Añade una nueva dirección a un cliente previamente registrado.")
    @ApiResponse(responseCode = "201", description = "Dirección agregada exitosamente.")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida (problemas de validación).")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    public ResponseEntity<DireccionCreatedResponse> createDireccion(@PathVariable Long clientId, @Valid @RequestBody CreateDireccionCommand command) {
        var response = clienteCommandService.addDireccionToCliente(clientId,command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{clientId}")
    @Operation(summary = "Actualizar los datos de un cliente", description = "Modifica la información básica de un cliente existente.")
    @ApiResponse(responseCode = "204", description = "Cliente actualizado exitosamente (sin contenido de respuesta).")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida (ID en URL no coincide con ID en cuerpo o validación fallida).")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    public ResponseEntity<Void> updateCliente(@PathVariable Long clientId,@Valid @RequestBody UpdateClienteCommand command){
        clienteCommandService.updateCliente(command);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{clienteId}/direcciones/{direccionId}")
    @Operation(summary = "Remover una dirección de un cliente", description = "Elimina una dirección específica de un cliente. La dirección es eliminada permanentemente.")
    @ApiResponse(responseCode = "204", description = "Dirección removida exitosamente.")
    @ApiResponse(responseCode = "404", description = "Cliente o dirección no encontrados.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    public ResponseEntity<Void> removeDireccionFromCliente(@PathVariable Long clienteId,@PathVariable Long direccionId) {
        RemoveDireccionFromClienteCommand command = new RemoveDireccionFromClienteCommand(clienteId, direccionId);
        clienteCommandService.removeDireccionFromCliente(command);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{clientId}")
    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente y todas sus direcciones asociadas de forma permanente.")
    @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente.")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long clientId){
        clienteCommandService.deleteCliente(clientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
