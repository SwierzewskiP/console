package pl.sda.view;

import pl.sda.dao.RunningEventDAO;
import pl.sda.dto.RunningEvent;
import pl.sda.view.table.TablePrinter;

import java.util.Scanner;

public class RunningEventManager {

    RunningEventDAO runningEventDAO = new RunningEventDAO();
    Scanner scanner = new Scanner(System.in);

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
        System.out.println("Podaj nazwę wydarzenia biegowego:");
        String name = scanner.nextLine();
        System.out.println("Podaj miejsce wydarzenia:");
        String place = scanner.nextLine();
        System.out.println("Wpisz liczbę kilometrów do przebiegnięcia:");
        String distance = scanner.nextLine();

        RunningEvent runningEvent = new RunningEvent(name, place, (Float.valueOf(distance)));

        runningEventDAO.create(runningEvent);
    }

    public void deleteRunningEvent() {
        System.out.println("Podaj Id wydarzenia, które chcesz usunąć");
        Long eventId = scanner.nextLong();

        runningEventDAO.deleteEvent(eventId);

        System.out.println("Wydarzenie o id " + eventId + " zostało usunięte.");
    }

}
