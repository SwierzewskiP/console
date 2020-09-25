package pl.sda.dao;

import pl.sda.dto.Runner;
import pl.sda.dto.RunningEvent;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalTime;
import java.util.List;

public class RunnerDAO {

    EntityManager entityManager = EntityManagerService.getEntityManager();

    public void create(Runner runner) {
        entityManager.getTransaction().begin();
        entityManager.persist(runner);
        entityManager.getTransaction().commit();
    }

    public List<Runner> readAll() {
        entityManager.getTransaction().begin();
        TypedQuery<Runner> query = entityManager.createQuery(
                "SELECT r FROM Runner r", Runner.class);
        List<Runner> runners = query.getResultList();
        entityManager.getTransaction().commit();

        return runners;
    }

    public Runner getRunnerById(Long runnerId) {
        entityManager.getTransaction().begin();
        Runner runner = entityManager.find(Runner.class, runnerId);
        entityManager.getTransaction().commit();

        return runner;
    }

    public void updateBestTime(Long runnerId, LocalTime newBestTime) {
        entityManager.getTransaction().begin();
        Runner runner = entityManager.find(Runner.class, runnerId);
        runner.setTenKmBestTime(newBestTime);
        entityManager.getTransaction().commit();
    }

    public void deleteRunner(Long runnerId) {
        entityManager.getTransaction().begin();
        Runner runner = entityManager.find(Runner.class, runnerId);
        entityManager.remove(runner);
        entityManager.getTransaction().commit();
    }
}
