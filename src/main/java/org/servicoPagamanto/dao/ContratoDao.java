package org.servicoPagamanto.dao;

import jakarta.persistence.EntityManager;
import org.servicoPagamanto.model.entities.Contrato;

import java.util.List;

public class ContratoDao {

    private EntityManager em;

    public ContratoDao(EntityManager em) {
      this.em = em;
    }

    public void salvar(Contrato contrato) {
        try {
            em.getTransaction().begin();
            em.persist(contrato);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }


    public Contrato buscarPorId(Long id) {
        return em.find(Contrato.class, id);
    }

    public void atualizar(Contrato contrato ) {
        try {
            em.getTransaction().begin();
            em.merge(contrato);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void remover(Long id) {
        Contrato contrato = em.find(Contrato.class, id);
        if (contrato == null) {
            throw new RuntimeException("Contrato não encontrado para o id: " + id);
        }
        try {
            em.getTransaction().begin();
            em.remove(contrato);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }


    public List<Contrato> listarTodos() {
        return em.createQuery("SELECT c FROM Contrato c", Contrato.class)
                .getResultList();
    }

    public void removerTodos() {
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Parcela p").executeUpdate();
            em.createQuery("DELETE FROM Contrato c").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }

    }

}