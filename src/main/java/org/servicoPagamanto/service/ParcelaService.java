package org.servicoPagamanto.service;

import org.servicoPagamanto.model.entities.Parcela;
import org.servicoPagamanto.repository.ParcelaRepository;
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

    public List<Parcela> listarTodos() {
        return repository.findAll();
    }
}