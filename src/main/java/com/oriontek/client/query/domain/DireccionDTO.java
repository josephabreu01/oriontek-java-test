package com.oriontek.client.query.domain;

public record DireccionDTO(
        Long id,
        String calle,
        String numero,
        String ciudad,
        String provincia,
        String codigoPostal
) { }
