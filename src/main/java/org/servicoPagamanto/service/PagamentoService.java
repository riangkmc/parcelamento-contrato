package org.servicoPagamanto.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface PagamentoService {
    public BigDecimal taxaPagamento(BigDecimal valor);
    public BigDecimal juros(BigDecimal valor,int meses);
}
