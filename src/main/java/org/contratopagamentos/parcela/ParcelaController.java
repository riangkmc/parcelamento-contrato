package org.contratopagamentos.parcela;

import org.contratopagamentos.contrato.ContratoResponse;
import org.contratopagamentos.contrato.ContratoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parcelas")
public class ParcelaController {

    private final ParcelaService parcelaService;

    public ParcelaController(ParcelaService parcelaService) {
        this.parcelaService = parcelaService;
    }

    @GetMapping
    public List<ParcelaResponse> listarTodos() {
        return parcelaService.listarTodos();
    }
}
