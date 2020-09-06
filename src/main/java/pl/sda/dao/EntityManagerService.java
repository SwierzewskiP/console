package pl.sda.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerService {

    private static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            create();
        }
        return entityManager;
    }

    public static void create() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa.hibernate");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
