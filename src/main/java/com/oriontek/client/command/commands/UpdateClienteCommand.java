package com.oriontek.client.command.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateClienteCommand(
        @NotNull(message = "El ID del cliente no puede ser nulo para una actualización.")
        Long id,

        @NotBlank(message = "El nombre del cliente no puede estar vacío.")
        @Size(max = 255, message = "El nombre no puede exceder los 255 caracteres.")
        String nombre,

        @NotBlank(message = "El RUC del cliente no puede estar vacío.")
        @Size(max = 50, message = "El RUC no puede exceder los 50 caracteres.")
        String ruc, // Considera si el RUC realmente debe ser modificable.

        @Email(message = "El formato del email es inválido.")
        @Size(max = 255, message = "El email no puede exceder los 255 caracteres.")
        String email
) {}