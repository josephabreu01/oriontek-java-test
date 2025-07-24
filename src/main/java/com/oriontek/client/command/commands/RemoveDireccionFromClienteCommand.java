package com.oriontek.client.command.commands;

import jakarta.validation.constraints.NotNull;

public record RemoveDireccionFromClienteCommand (
        @NotNull(message = "El ID del cliente no puede ser nulo para remover una dirección.")
        Long clienteId,

        @NotNull(message = "El ID de la dirección a remover no puede ser nulo.")
        Long direccionId
){}
