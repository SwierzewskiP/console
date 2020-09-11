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

//    public List<Run> readRunTimesOfARunner(Long runnerId) {
//        entityManager.getTransaction().begin();
//        TypedQuery<Run> query = entityManager.createQuery(
//                "SELECT r FROM Run r WHERE r.runner_id" = r, Run.class);
//        List<Run> runs = query.getResultList();
//
//        entityManager.getTransaction().commit();
//        return runs;
//    }
//
//    public void updateRunTime(int runnerId, LocalTime currentTime) {
//        entityManager.getTransaction().begin();
//        TypedQuery<Run> typedQuery = entityManager.createQuery(
//                "SELECT r FROM Run r WHERE r.runner_id = :runnerId AND r.running_event_id = :eventId", Run.class);
//        typedQuery.setParameter("id1", 1, "id2", 1);
//        entityManager.getTransaction().commit();
//    }


}
