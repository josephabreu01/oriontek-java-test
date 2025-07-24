package com.oriontek.client.domain.repository;

import com.oriontek.client.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteCommandRepository extends JpaRepository<Cliente, Long> {
}
