package pl.sda.dao;

import pl.sda.dto.Runner;
import pl.sda.dto.RunningEvent;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalTime;
import java.util.List;

public class RunningEventDAO {

    EntityManager entityManager = EntityManagerService.getEntityManager();

    public void create(RunningEvent runningEvent) {
        entityManager.getTransaction().begin();
        entityManager.persist(runningEvent);
        entityManager.getTransaction().commit();
    }

    public List<RunningEvent> readAll() {
        entityManager.getTransaction().begin();
        TypedQuery<RunningEvent> query = entityManager.createQuery(
                "SELECT re FROM RunningEvent re", RunningEvent.class);
        List<RunningEvent> events = query.getResultList();
        entityManager.getTransaction().commit();

        return events;
    }

    public RunningEvent getEventById(Long eventId) {
        entityManager.getTransaction().begin();
        RunningEvent runningEvent = entityManager.find(RunningEvent.class, eventId);
        entityManager.getTransaction().commit();

        return runningEvent;
    }

    public void deleteEvent(Long eventId) {
        entityManager.getTransaction().begin();
        RunningEvent runningEvent = entityManager.find(RunningEvent.class, eventId);
        entityManager.remove(runningEvent);
        entityManager.getTransaction().commit();
    }
}

