package com.oriontek.client.command.commands;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;


public record CreateDireccionCommand(
        @NotBlank(message = "La calle no puede estar vacía.")
        @Size(max = 255, message = "La calle no puede exceder los 255 caracteres.")
        String calle,

        @Size(max = 50, message = "El número no puede exceder los 50 caracteres.")
        String numero,

        @NotBlank(message = "La ciudad no puede estar vacía.")
        @Size(max = 100, message = "La ciudad no puede exceder los 100 caracteres.")
        String ciudad,

        @NotBlank(message = "La provincia no puede estar vacía.")
        @Size(max = 100, message = "La provincia no puede exceder los 100 caracteres.")
        String provincia,

        @Size(max = 20, message = "El código postal no puede exceder los 20 caracteres.")
        String codigoPostal
) {}
