package com.oriontek.client.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ruc;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    private List<Direccion> direcciones = new ArrayList<>();

    public void agregarDireccion(Direccion direccion) {
        this.direcciones.add(direccion);
        direccion.setCliente(this);
    }

    public void removerDireccion(Direccion direccion) {
        this.direcciones.remove(direccion);
        direccion.setCliente(null);
    }
    public boolean removerDireccion(Long direccionId) {
        Optional<Direccion> direccionToRemove = this.direcciones.stream()
                .filter(dir -> dir.getId() != null && dir.getId().equals(direccionId))
                .findFirst();

        if (direccionToRemove.isPresent()) {
            Direccion dir = direccionToRemove.get();
            this.direcciones.remove(dir);
            dir.setCliente(null);
            return true;
        }
        return false;
    }
}
