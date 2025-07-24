package com.oriontek.client.query.controller;

import com.oriontek.client.query.domain.ClienteDetailsDTO;
import com.oriontek.client.query.domain.ClienteListDTO;
import com.oriontek.client.query.service.ClienteQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes/queries")
public class ClienteQueryController {
    private final ClienteQueryService clienteQueryService;

    public ClienteQueryController(ClienteQueryService clienteQueryService) {
        this.clienteQueryService = clienteQueryService;
    }

    @GetMapping
    @Operation(summary = "Obtener lista de todos los clientes", description = "Recupera un listado resumido de todos los clientes existentes en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    public ResponseEntity<List<ClienteListDTO>> getAllClientes() {
        var response = clienteQueryService.getAllClientes();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalles de un cliente por ID",description = "Recupera la información completa de un cliente específico, incluyendo todas sus direcciones.")
    @ApiResponse(responseCode = "200", description = "Detalles del cliente encontrados exitosamente.")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado para el ID proporcionado.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    public ResponseEntity<ClienteDetailsDTO> getClienteDetails(@PathVariable Long id) {
        ClienteDetailsDTO clienteDetails = clienteQueryService.getClienteDetails(id);
        return clienteDetails != null ? ResponseEntity.ok(clienteDetails) : ResponseEntity.notFound().build();
    }
}
