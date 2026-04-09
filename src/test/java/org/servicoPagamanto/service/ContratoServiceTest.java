package org.servicoPagamanto.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.servicoPagamanto.model.entities.Contrato;


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContratoServiceTest {

    Contrato contrato;

    @Mock
    PayPalService payPalService;

    @InjectMocks
    private ContratoService contratoService;

    @BeforeEach
    void setUp() {
        when(payPalService.juros(any(), anyInt())).thenCallRealMethod();
        when(payPalService.taxaPagamento(any())).thenCallRealMethod();
        contrato = new Contrato();
        contrato.setDataContrato(LocalDate.of(2026, 4, 5));
        contrato.setValorTotal(new BigDecimal("600.00"));
    }



    @Test
    @DisplayName("devo criar a quantidade de parcelas correta para cada contrato")
    void  deveCriarQuantidadeCorretaDeParcelas() {

        contratoService.processarContrato(contrato, 3);

        assertEquals(3, contrato.getParcelas().size());
    }
    @Test
    void deveProcessarContratoComUmaParcela() {
        contratoService.processarContrato(contrato, 1);
        assertEquals(1, contrato.getParcelas().size());
    }

    @Test
    void deveGerarDatasCorretasDeVencimento() {

        contratoService.processarContrato(contrato, 3);

        assertEquals(LocalDate.of(2026, 5, 5), contrato.getParcelas().get(0).getDataVencimento());
        assertEquals(LocalDate.of(2026, 6, 5), contrato.getParcelas().get(1).getDataVencimento());
        assertEquals(LocalDate.of(2026, 7, 5), contrato.getParcelas().get(2).getDataVencimento());
    }

    @Test
    void deveGerarDataCorretaParaContratoNoUltimoDiaDoMes() {
        contrato.setDataContrato(LocalDate.of(2026, 1, 31));
        contratoService.processarContrato(contrato, 1);
        assertEquals(LocalDate.of(2026, 2, 28), contrato.getParcelas().get(0).getDataVencimento());
    }


    @Test
    void deveCalcularValorCorretoDasParcelas() {

        contratoService.processarContrato(contrato,3);

        assertEquals(0, new BigDecimal("206.04").compareTo(contrato.getParcelas().get(0).getValor()));
        assertEquals(0, new BigDecimal("208.08").compareTo(contrato.getParcelas().get(1).getValor()));
        assertEquals(0, new BigDecimal("210.12").compareTo(contrato.getParcelas().get(2).getValor()));
    }

    @Test
    void deveCalcularValorFinalDoContratoComJuros() {
        contratoService.processarContrato(contrato, 3);
        BigDecimal total = contratoService.getValorComJurosContrato(contrato);

        assertEquals(0, new BigDecimal("624.24").compareTo(total));
    }
}