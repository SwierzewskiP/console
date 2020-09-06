package pl.sda.dao;

import pl.sda.dto.Runner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RunnerDAO {

    EntityManager entityManager = EntityManagerService.getEntityManager();

    public void create(Runner runner) {
        entityManager.getTransaction().begin();
        entityManager.persist(runner);
        entityManager.getTransaction().commit();
    }

    public List<Runner> read() {
        entityManager.getTransaction().begin();
        TypedQuery<Runner> query = entityManager.createQuery(
                "SELECT r FROM Runner r", Runner.class);
        List<Runner> runners = query.getResultList();
        entityManager.getTransaction().commit();
    return runners;
    }
}
