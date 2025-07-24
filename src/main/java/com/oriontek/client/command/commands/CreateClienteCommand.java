package com.oriontek.client.command.commands;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateClienteCommand(
        @NotBlank(message = "El nombre del cliente no puede estar vacío.")
        @Size(max = 255, message = "El nombre no puede exceder los 255 caracteres.")
        String nombre,

        @NotBlank(message = "El RUC del cliente no puede estar vacío.")
        @Size(max = 50, message = "El RUC no puede exceder los 50 caracteres.")
        String ruc,

        @Email(message = "El formato del email es inválido.")
        @Size(max = 255, message = "El email no puede exceder los 255 caracteres.")
        String email,

        List<@Valid CreateDireccionCommand> direcciones
) {}
