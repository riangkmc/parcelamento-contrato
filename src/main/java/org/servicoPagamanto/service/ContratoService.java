package org.servicoPagamanto.service;

import org.servicoPagamanto.model.entities.Contrato;
import org.servicoPagamanto.model.entities.Parcela;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


public class ContratoService {

    private PagamentoService pagamentoService;

    public ContratoService(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    public void processarContrato(Contrato contrato, int parcelas){
        if (parcelas <= 0) {
            throw new IllegalArgumentException("Parcelas deve ser maior que zero");
        }
        BigDecimal valorParcelaContrato = contrato.getValorTotal().divide(BigDecimal.valueOf(parcelas),2, RoundingMode.HALF_UP) ;
        for (int i = 1; i <= parcelas; i++) {
            //as proximas linhas calculam as taxas(juros e final) sobre a parcela
            BigDecimal valorComJuros = pagamentoService.juros(valorParcelaContrato,i).add(valorParcelaContrato);
            BigDecimal valorFinalParcela = pagamentoService.taxaPagamento(valorComJuros).add(valorComJuros);
            LocalDate dataVencimento = contrato.getDataContrato().plusMonths(i);
            Parcela parcela = new Parcela(dataVencimento,valorFinalParcela);
            contrato.adicionarParcela(parcela);
        }
    }

    public BigDecimal getValorComJurosContrato(Contrato contrato){
        BigDecimal soma = BigDecimal.ZERO;
        for(Parcela parcela : contrato.getParcelas()){
            soma = soma.add(parcela.getValor());
        }
        return soma;
    }
}
