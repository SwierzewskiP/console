package pl.sda.view;

import pl.sda.dao.RunningEventDAO;
import pl.sda.dto.RunningEvent;
import pl.sda.view.table.TablePrinter;

import java.util.Scanner;

public class RunningEventManager {

    RunningEventDAO runningEventDAO = new RunningEventDAO();

    public void printList() {
        new TablePrinter<RunningEvent>()
                .withData(runningEventDAO.readAll())
                .withColumn("Id wydarzenia", event -> String.valueOf(event.getId()))
                .withColumn("Nazwa", RunningEvent::getName)
                .withColumn("Miejsce", RunningEvent::getPlace)
                .withColumn("Dystans (km)", RunningEvent::getDistanceInKmsAsString)
                .printTable();
    }

    public void addRunningEvent() {
        Scanner s = new Scanner(System.in);
        System.out.println("Podaj nazwę wydarzenia biegowego:");
        String name = s.nextLine();
        System.out.println("Podaj miejsce wydarzenia:");
        String place = s.nextLine();
        System.out.println("Wpisz liczbę kilometrów do przebiegnięcia:");
        String distance = s.nextLine();

        RunningEvent runningEvent = new RunningEvent(name, place, (Float.valueOf(distance)));

        runningEventDAO.create(runningEvent);
    }
}
