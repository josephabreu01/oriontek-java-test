package com.oriontek.client.query.domain;

public record ClienteListDTO(
        Long id,
        String nombre,
        String ruc,
        String email
) {}
