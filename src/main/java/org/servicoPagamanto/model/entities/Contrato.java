package org.servicoPagamanto.model.entities;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "contrato")
public class Contrato extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "numero_contrato")
    private long numeroContrato;

    @Column(name = "data_contrato")
    private LocalDate dataContrato;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "contrato",cascade = CascadeType.ALL)
    private List<Parcela> parcelas = new ArrayList<>();

    public Contrato(long numeroContrato, LocalDate dataContrato, BigDecimal valorTotal) {
        this.numeroContrato = numeroContrato;
        this.dataContrato = dataContrato;
        this.valorTotal = valorTotal;
    }

    public Contrato() {
    }

    public List<Parcela> getParcelas() {
        return Collections.unmodifiableList(parcelas);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(long numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public LocalDate getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(LocalDate dataContrato) {
        this.dataContrato = dataContrato;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void adicionarParcela(Parcela parcela){
        parcelas.add(parcela);
        parcela.setContrato(this);
    }

    public void removerParcela(Parcela parcela){
        parcelas.remove(parcela);
        parcela.setContrato(null);
    }

    @Override
    public String toString() {
        return  "Contrato: " +
                "id="+ id +
                " numeroContrato=" + numeroContrato +
                ", dataContrato=" + dataContrato +
                ", valorTotal=" + valorTotal +
                ',' + super.toString() ;
    }
}
