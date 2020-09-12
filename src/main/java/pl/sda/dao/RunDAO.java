package pl.sda.dao;

import pl.sda.dto.Run;
import pl.sda.dto.Runner;
import pl.sda.dto.RunningEvent;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

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
                "SELECT r FROM Run r JOIN r.runner rr WHERE r.runningEvent = :runningEvent " +
                        "ORDER BY rr.lastName, rr.firstName", Run.class);
        typedQuery.setParameter("runningEvent", runningEvent);
        List<Run> runs = typedQuery.getResultList();
        entityManager.getTransaction().commit();

        return runs;
    }

    public List<Run> readAllRunsOfSpecificEvent(RunningEvent runningEvent) {
        entityManager.getTransaction().begin();
        TypedQuery<Run> typedQuery = entityManager.createQuery(
                "SELECT r FROM Run r WHERE r.runningEvent = :runningEvent ORDER BY r.runTime", Run.class);
        typedQuery.setParameter("runningEvent", runningEvent);
        List<Run> runs = typedQuery.getResultList();
        entityManager.getTransaction().commit();

        return runs;
    }

    public void updateRunTime(Runner runner, RunningEvent runningEvent, LocalTime runTime) {
        entityManager.getTransaction().begin();
        TypedQuery<Run> typedQuery = entityManager.createQuery(
                "SELECT r FROM Run r WHERE r.runner = :runner AND r.runningEvent = :runningEvent", Run.class);
        typedQuery.setParameter("runner", runner)
                .setParameter("runningEvent", runningEvent);
        Run run = typedQuery.getSingleResult();
        run.setRunTime(runTime);
        entityManager.getTransaction().commit();
    }
}
