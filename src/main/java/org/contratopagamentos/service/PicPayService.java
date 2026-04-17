package org.contratopagamentos.service;

import org.contratopagamentos.model.enums.TipoPagamento;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PicPayService implements PagamentoService{

    public TipoPagamento getTipo() {
        return TipoPagamento.PICPAY;
    }

    public BigDecimal taxaPagamento(BigDecimal valor){
        BigDecimal taxa = new BigDecimal("0.03");
        return valor.multiply(taxa);
    }

    public BigDecimal juros(BigDecimal valor,int meses){
        BigDecimal taxa = new BigDecimal("0.02");
        BigDecimal taxaMeses = taxa.multiply(new BigDecimal(meses));
        return valor.multiply(taxaMeses);
    }
}
