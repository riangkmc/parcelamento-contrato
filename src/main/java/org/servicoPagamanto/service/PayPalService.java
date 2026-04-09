package org.servicoPagamanto.service;

import java.math.BigDecimal;

public class PayPalService implements PagamentoService {

    public BigDecimal taxaPagamento(BigDecimal valor){
        BigDecimal taxa = new BigDecimal("0.02");
        return valor.multiply(taxa);
    }

    public BigDecimal juros(BigDecimal valor,int meses){
        BigDecimal taxa = new BigDecimal("0.01");
        BigDecimal taxaMeses = taxa.multiply(new BigDecimal(meses));
        return valor.multiply(taxaMeses);
    }
}
