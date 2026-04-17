package org.contratopagamentos.contrato;

import org.contratopagamentos.parcela.ParcelaResponse;

public class ContratoMapper {

    public static ContratoResponse toResponse(Contrato contrato) {
        ContratoResponse dto = new ContratoResponse();

        dto.setId(contrato.getId());
        dto.setNumeroContrato(contrato.getNumeroContrato());
        dto.setDataContrato(contrato.getDataContrato());
        dto.setValorTotal(contrato.getValorTotal());
        dto.setTipoPagamento(contrato.getTipoPagamento());
        dto.setCriadoEm(contrato.getCriadoEm());
        dto.setAtualizadoEm(contrato.getAtualizadoEm());
        dto.setParcelas(
                contrato.getParcelas().stream()
                        .map(p -> {
                            ParcelaResponse pr = new ParcelaResponse();
                            pr.setId(p.getId());
                            pr.setDataVencimento(p.getDataVencimento());
                            pr.setValor(p.getValor());
                            return pr;
                        })
                        .toList()
        );
        return dto;
    }
}