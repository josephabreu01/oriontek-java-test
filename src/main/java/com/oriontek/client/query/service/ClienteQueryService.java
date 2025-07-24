package com.oriontek.client.query.service;

import com.oriontek.client.common.exceptions.ClienteNotFoundException;
import com.oriontek.client.query.domain.ClienteDetailsDTO;
import com.oriontek.client.query.domain.ClienteListDTO;
import com.oriontek.client.query.domain.DireccionDTO;
import com.oriontek.client.domain.repository.ClienteQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteQueryService {

    private final ClienteQueryRepository clienteQueryRepository;

    public ClienteQueryService(ClienteQueryRepository clienteQueryRepository) {
        this.clienteQueryRepository = clienteQueryRepository;
    }


    public ClienteDetailsDTO getClienteDetails(Long id) {
        return clienteQueryRepository.findById(id).map(cliente -> new ClienteDetailsDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getRuc(),
                cliente.getEmail(),
                cliente.getDirecciones().stream()
                        .map(dir ->
                                new DireccionDTO(dir.getId(), dir.getCalle(), dir.getNumero(), dir.getCiudad(), dir.getProvincia(), dir.getCodigoPostal()))
                        .collect(Collectors.toList())
        )).orElseThrow(() ->new ClienteNotFoundException("No se contro el cliente con ID " + id));
    }

    public List<ClienteListDTO> getAllClientes() {
        return clienteQueryRepository.findAll().stream()
                .map(cliente -> new ClienteListDTO(
                        cliente.getId(),
                        cliente.getNombre(),
                        cliente.getRuc(),
                        cliente.getEmail()))
                .collect(Collectors.toList());
    }
}
