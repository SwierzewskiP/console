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

        EntityManager entityManager = EntityManagerService.getEntityManager();

        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.start();

        EntityManagerService.close();
    }


}
