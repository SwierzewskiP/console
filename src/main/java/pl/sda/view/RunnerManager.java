package pl.sda.view;

import pl.sda.dao.RunnerDAO;
import pl.sda.dto.Runner;
import pl.sda.view.table.TablePrinter;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class RunnerManager {

    RunnerDAO runnerDAO = new RunnerDAO();

    public void printList() {
        new TablePrinter<Runner>()
                .withData(runnerDAO.read())
                .withColumn("Imię", Runner::getFirstName)
                .withColumn("Nazwisko", Runner::getLastName)
                .withColumn("Najlepszy czas", Runner::getTenKmBestTimeAsString)
                .withColumn("Aktualny czas", Runner::getCurrentRunTimeAsString)
                .withColumn("Lista biegów", runner -> String.valueOf(runner.getListOfEvents()))
                .printTable();
    }

    public void addRunner() {
        Scanner s = new Scanner(System.in);
        System.out.println("Podaj imię:");
        String firstName = s.nextLine();
        System.out.println("Podaj nazwisko:");
        String lastName = s.nextLine();
        System.out.println("Podaj najlepszy czas na 10 km");


    }


}
