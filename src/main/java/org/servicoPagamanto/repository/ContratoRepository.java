package org.servicoPagamanto.repository;

import org.servicoPagamanto.model.entities.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<Contrato,Long> {
}
