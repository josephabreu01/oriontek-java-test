package com.oriontek.client.domain.repository;

import com.oriontek.client.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteQueryRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.direcciones WHERE c.id = :id")
    Optional<Cliente> findByIdWithDirecciones(Long id);

    List<Cliente> findAllByOrderByNombreAsc();
}
