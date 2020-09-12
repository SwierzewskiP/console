package pl.sda.dao;

import pl.sda.dto.Run;
import pl.sda.dto.Runner;
import pl.sda.dto.RunningEvent;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalTime;
import java.util.List;

public class RunDAO {

    EntityManager entityManager = EntityManagerService.getEntityManager();

    public void create(Run run) {
        entityManager.getTransaction().begin();
        entityManager.persist(run);
        entityManager.getTransaction().commit();
    }

    public List<Run> readAllRunsOfSpecificRunner(Runner runner) {
        entityManager.getTransaction().begin();
        TypedQuery<Run> typedQuery = entityManager.createQuery(
                "SELECT r FROM Run r WHERE r.runner = :runner", Run.class);
        typedQuery.setParameter("runner", runner);
        List<Run> runs = typedQuery.getResultList();
        entityManager.getTransaction().commit();

        return runs;
    }

    public List<Run> readAllRunnersOfSpecificEvent(RunningEvent runningEvent) {
        entityManager.getTransaction().begin();
        TypedQuery<Run> typedQuery = entityManager.createQuery(
                "SELECT r FROM Run r WHERE r.runningEvent = :runningEvent", Run.class);
        typedQuery.setParameter("runningEvent", runningEvent);
        List<Run> runs = typedQuery.getResultList();
        entityManager.getTransaction().commit();

        return runs;
    }


}
