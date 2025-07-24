package com.oriontek.client.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle;
    private String numero;
    private String ciudad;
    private String provincia;
    private String codigoPostal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Cliente cliente;

}
