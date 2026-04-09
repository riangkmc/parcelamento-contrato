package org.servicoPagamanto.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.function.Function;

public class JpaConnection {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("servico-pagamento");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Executa uma operação em transação local com rollback automático em caso de erro.
    public static <T> T executeInTransaction(Function<EntityManager, T> action) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            T result = action.apply(entityManager);
            entityManager.getTransaction().commit();
            return result;
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

}
