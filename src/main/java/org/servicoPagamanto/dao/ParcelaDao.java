package org.servicoPagamanto.dao;

import jakarta.persistence.EntityManager;
import org.servicoPagamanto.model.entities.Parcela;

import java.util.List;

public class ParcelaDao {
    
    private EntityManager em;

    public ParcelaDao(EntityManager em) {
        this.em = em;
    }

    public void salvar(Parcela parcela) {
        em.getTransaction().begin();
        em.persist(parcela);
        em.getTransaction().commit();
    }

    public void atualizar(Parcela parcela) {
        em.getTransaction().begin();
        em.merge(parcela);
        em.getTransaction().commit();
    }

    public void remover(Long id) {
        Parcela parcela = em.find(Parcela.class, id);
        if (parcela != null) {
            em.getTransaction().begin();
            em.remove(parcela);
            em.getTransaction().commit();
        }
    }

    public Parcela buscarPorId(Long id) {
        return em.find(Parcela.class, id);
    }

    public List<Parcela> listarTodos() {
        return em.createQuery("SELECT c FROM Parcela c", Parcela.class)
                .getResultList();
    }

    public void removerTodos() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Parcela p").executeUpdate();
        em.getTransaction().commit();
    }


}