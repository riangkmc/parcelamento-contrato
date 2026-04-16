package org.servicoPagamanto.service;

import jakarta.transaction.Transactional;
import org.servicoPagamanto.model.entities.Contrato;
import org.servicoPagamanto.model.entities.Parcela;
import org.servicoPagamanto.repository.ContratoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;


    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    public void processarContrato(Contrato contrato, int parcelas) {
        if (parcelas <= 0) {
            throw new IllegalArgumentException("Parcelas deve ser maior que zero");
        }
        BigDecimal valorParcelaContrato = contrato.getValorTotal().divide(BigDecimal.valueOf(parcelas), 2, RoundingMode.HALF_UP);
        for (int i = 1; i <= parcelas; i++) {
            BigDecimal valorComJuros = contrato.getPagamentoService().juros(valorParcelaContrato, i).add(valorParcelaContrato);
            BigDecimal valorFinalParcela = contrato.getPagamentoService().taxaPagamento(valorComJuros).add(valorComJuros);
            LocalDate dataVencimento = contrato.getDataContrato().plusMonths(i);
            Parcela parcela = new Parcela(dataVencimento, valorFinalParcela);
            contrato.adicionarParcela(parcela);
        }
    }
    @Transactional
    public BigDecimal getValorComJurosContrato(Long id) {
        Contrato contrato = contratoRepository.findById(id)

                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
        BigDecimal soma = BigDecimal.ZERO;
        for (Parcela parcela : contrato.getParcelas()) {
            soma = soma.add(parcela.getValor());
        }
        return soma;
    }

    public Contrato salvar(Contrato contrato) {
        return contratoRepository.save(contrato);
    }


    @Transactional
    public Contrato buscarPorId(Long id) {
        return contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
    }

    public List<Contrato> listarTodos() {
        return contratoRepository.findAll();
    }

    public Contrato atualizar(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    public void remover(Long id) {
        if (!contratoRepository.existsById(id)) {
            throw new RuntimeException("Contrato não encontrado");
        }
        contratoRepository.deleteById(id);
    }

    public void removerTodos() {
        contratoRepository.deleteAll();
    }

    @Transactional
    public void imprimirParcelas(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));

        for (Parcela p : contrato.getParcelas()) {
            System.out.println(p);
        }
    }
}