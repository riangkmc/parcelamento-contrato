package org.servicoPagamanto.service;

import java.math.BigDecimal;

public interface PagamentoService {
    public BigDecimal taxaPagamento(BigDecimal valor);
    public BigDecimal juros(BigDecimal valor,int meses);
}
