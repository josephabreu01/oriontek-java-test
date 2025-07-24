package com.oriontek.client.query.domain;

import java.util.List;

public record ClienteDetailsDTO (
        Long id,
        String nombre,
        String ruc,
        String email,
        List<DireccionDTO> direcciones
        ){ }
