package com.oriontek.client.command.service;

import com.oriontek.client.command.commands.RemoveDireccionFromClienteCommand;
import com.oriontek.client.command.controller.responses.ClienteCreatedResponse;
import com.oriontek.client.command.controller.responses.DireccionCreatedResponse;
import com.oriontek.client.common.exceptions.DireccionNotFoundException;
import com.oriontek.client.domain.model.Cliente;
import com.oriontek.client.domain.model.Direccion;
import com.oriontek.client.domain.repository.ClienteCommandRepository;
import com.oriontek.client.command.commands.CreateClienteCommand;
import com.oriontek.client.command.commands.CreateDireccionCommand;
import com.oriontek.client.command.commands.UpdateClienteCommand;
import com.oriontek.client.common.exceptions.ClienteNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class ClienteCommandService {

    private final ClienteCommandRepository clienteCommandRepository;



    public ClienteCommandService(ClienteCommandRepository clienteCommandRepository) {
        this.clienteCommandRepository = clienteCommandRepository;
    }

    @Transactional
    public ClienteCreatedResponse createCliente(CreateClienteCommand command) {

        var cliente = new Cliente();
        cliente.setNombre(command.nombre());
        cliente.setRuc(command.ruc());
        cliente.setEmail(command.email());

        if (command.direcciones() != null && !command.direcciones().isEmpty()) {
            command.direcciones().forEach(dirCommand -> {
                newDirection(dirCommand, cliente);
            });
        }
        var savedCliente = clienteCommandRepository.save(cliente);
        return new ClienteCreatedResponse(savedCliente.getId(), "Cliente creado exitosamente.");
    }

    @Transactional
    public DireccionCreatedResponse addDireccionToCliente(Long clienteId, CreateDireccionCommand command) {

        var cliente = clienteCommandRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + clienteId + " no encontrado para agregar dirección."));

        newDirection(command, cliente);

        clienteCommandRepository.save(cliente);

        return new DireccionCreatedResponse(clienteId,"Dirección agregada exitosamente.");
    }



    @Transactional
    public void updateCliente(UpdateClienteCommand command) {

        var cliente = clienteCommandRepository.findById(command.id())
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + command.id() + " no encontrado para agregar dirección."));

        cliente.setNombre(command.nombre());
        cliente.setRuc(command.ruc());
        cliente.setEmail(command.email());
        clienteCommandRepository.save(cliente);
    }

    @Transactional
    public void removeDireccionFromCliente(RemoveDireccionFromClienteCommand command) {

        var cliente = clienteCommandRepository.findById(command.clienteId())
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + command.clienteId() + " no encontrado."));

        boolean removed = cliente.removerDireccion(command.direccionId());
        if (!removed) {
            throw new DireccionNotFoundException("Dirección con ID " + command.direccionId() + " no encontrada para el cliente con ID " + command.clienteId() + ".");
        }
        clienteCommandRepository.save(cliente);
    }

    @Transactional
    public void deleteCliente(Long clienteId) {

        if (!clienteCommandRepository.existsById(clienteId)) {
            throw new ClienteNotFoundException("Cliente con ID " + clienteId + " no encontrado para eliminar.");
        }
        clienteCommandRepository.deleteById(clienteId);
    }

    private void newDirection(CreateDireccionCommand command, Cliente cliente) {
        var nuevaDireccion = new Direccion();
        nuevaDireccion.setCalle(command.calle());
        nuevaDireccion.setNumero(command.numero());
        nuevaDireccion.setCiudad(command.ciudad());
        nuevaDireccion.setProvincia(command.provincia());
        nuevaDireccion.setCodigoPostal(command.codigoPostal());

        cliente.agregarDireccion(nuevaDireccion);
    }
}
