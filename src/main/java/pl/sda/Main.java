package pl.sda;

import pl.sda.dao.EntityManagerService;
import pl.sda.dto.Runner;
import pl.sda.dto.RunningEvent;
import pl.sda.view.ConsoleManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        Runner runner1 = new Runner("Jan", "Nowak", LocalTime.of(0,56,24),
//                LocalTime.of(0,57,20));
//        Runner runner2 = new Runner("Kamil", "Kowalski", LocalTime.of(0,45,34),
//                LocalTime.of(0,47,53));
//
//        RunningEvent runningEvent1 = new RunningEvent("GdyMaraton", "Gdynia", 42);
//        RunningEvent runningEvent2 = new RunningEvent("3City Run", "Gdańsk", 10);
//
//        runner1.setListOfEvents(List.of(runningEvent2));
//        runner2.setListOfEvents(List.of(runningEvent1, runningEvent2));

        EntityManager entityManager = EntityManagerService.getEntityManager();
//
//        entityManager.getTransaction().begin();
//        entityManager.persist(runner1);
//        entityManager.persist(runner2);
//        entityManager.getTransaction().commit();

        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.start();

        EntityManagerService.close();
    }


}
