package org.contratopagamentos.service;

import org.contratopagamentos.model.enums.TipoPagamento;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface PagamentoService {
    TipoPagamento getTipo();
    public BigDecimal taxaPagamento(BigDecimal valor);
    public BigDecimal juros(BigDecimal valor,int meses);
}
