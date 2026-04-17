package org.contratopagamentos.parcela;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelaService {

    private final ParcelaRepository repository;

    public ParcelaService(ParcelaRepository repository) {
        this.repository = repository;
    }

    public Parcela buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parcela não encontrada"));
    }

    public static ParcelaResponse toResponse(Parcela parcela) {
        ParcelaResponse dto = new ParcelaResponse();
        dto.setId(parcela.getId());
        dto.setDataVencimento(parcela.getDataVencimento());
        dto.setValor(parcela.getValor());
        return dto;
    }


    public List<ParcelaResponse> listarTodos() {
        return repository.findAll().stream()
                .map(parcela -> this.toResponse(parcela)).toList();
    }
}