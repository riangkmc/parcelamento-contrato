package org.contratopagamentos.repository;

import org.contratopagamentos.model.entities.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<Contrato,Long> {
}
