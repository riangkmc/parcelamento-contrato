package org.contratopagamentos.repository;

import org.contratopagamentos.model.entities.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelaRepository extends JpaRepository<Parcela,Long> {
}
