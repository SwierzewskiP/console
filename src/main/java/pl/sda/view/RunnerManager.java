package pl.sda.view;

import pl.sda.dao.RunnerDAO;
import pl.sda.dto.Runner;
import pl.sda.view.table.TablePrinter;

import java.time.LocalTime;
import java.util.Scanner;

public class RunnerManager {

    RunnerDAO runnerDAO = new RunnerDAO();
    Scanner scanner = new Scanner(System.in);

    public void printList() {
        new TablePrinter<Runner>()
                .withData(runnerDAO.readAll())
                .withColumn("Id biegacza", runner -> String.valueOf(runner.getId()))
                .withColumn("Imię", Runner::getFirstName)
                .withColumn("Nazwisko", Runner::getLastName)
                .withColumn("Czas na 10 km", Runner::getTenKmBestTimeAsString)
//                .withColumn("Aktualny czas", Runner::getCurrentRunTimeAsString)
//                .withColumn("Lista biegów", runner -> String.valueOf(runner.getListOfEvents()))
                .printTable();
    }

    public void addRunner() {
            System.out.println("Podaj imię:");
            String firstName = scanner.nextLine();
            System.out.println("Podaj nazwisko:");
            String lastName = scanner.nextLine();
            boolean isFormatCorrect = false;
            LocalTime bestTime = null;
            while(!isFormatCorrect) {
                System.out.println("Podaj najlepszy czas na 10 km (poprawny format HH:MM:SS):");
                String bestTimeString = scanner.nextLine();
                try {
                    bestTime = LocalTime.parse(bestTimeString);
                    isFormatCorrect=true;
                } catch (Exception e) {
                    System.out.println("Czas został podany w złym formacie!");
                }
            }
            Runner runner = new Runner(firstName, lastName, bestTime);

            runnerDAO.create(runner);
        }

    public void deleteRunner() {
        System.out.println("Podaj Id biegacza, którego chcesz usunąć");
        Long runnerId = scanner.nextLong();

        runnerDAO.deleteRunner(runnerId);

        System.out.println("Biegacz o id " + runnerId + " został usunięty.");
    }
}
