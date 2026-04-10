package org.servicoPagamanto.dao;

import jakarta.persistence.EntityManager;
import org.servicoPagamanto.model.entities.Parcela;

import java.util.List;

public class ParcelaDao {
    
    private EntityManager em;

    public ParcelaDao(EntityManager em) {
        this.em = em;
    }



    public void atualizar(Parcela parcela) {
        em.getTransaction().begin();
        em.merge(parcela);
        em.getTransaction().commit();
    }

    public Parcela buscarPorId(Long id) {
        return em.find(Parcela.class, id);
    }

    public List<Parcela> listarTodos() {
        return em.createQuery("SELECT c FROM Parcela c", Parcela.class)
                .getResultList();
    }


}