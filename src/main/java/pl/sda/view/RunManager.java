package pl.sda.view;

import pl.sda.dao.RunDAO;
import pl.sda.dao.RunnerDAO;
import pl.sda.dao.RunningEventDAO;
import pl.sda.dto.Run;
import pl.sda.dto.Runner;
import pl.sda.dto.RunningEvent;
import pl.sda.view.table.TablePrinter;

import java.time.LocalTime;
import java.util.Scanner;

public class RunManager {

    RunDAO runDAO = new RunDAO();
    RunnerDAO runnerDAO = new RunnerDAO();
    RunningEventDAO runningEventDAO = new RunningEventDAO();

    Scanner scanner = new Scanner(System.in);

    public void printAllRunsOfOneRunner() {
        System.out.println("Podaj id biegacza:");
        Long runnerId = scanner.nextLong();
        Runner runner = runnerDAO.getRunnerById(runnerId);
        System.out.println("Biegi, w których bierze udział " + runner + ":");
        new TablePrinter<Run>()
                .withData(runDAO.readAllRunsOfSpecificRunner(runner))
//                .withColumn("Dane biegacza", Run::getRunnerAsString)
                .withColumn("Nazwa eventu", Run::getRunningEventAsString)
                .withColumn("Osiągnięty czas", Run::getRunTimeAsString)
//                .withColumn("Aktualny czas", Runner::getCurrentRunTimeAsString)
//                .withColumn("Lista biegów", runner -> String.valueOf(runner.getListOfEvents()))
                .printTable();
    }

    public void printAllRunnersOfAnEvent() {
        System.out.println("Podaj id eventu:");
        Long eventId = scanner.nextLong();
        RunningEvent runningEvent = runningEventDAO.getEventById(eventId);
        System.out.println("Lista uczestników biegu " + runningEvent + ":");
        new TablePrinter<Run>()
                .withData(runDAO.readAllRunnersOfSpecificEvent(runningEvent))
                .withColumn("Dane biegacza", Run::getRunnerAsString)
//                .withColumn("Osiągnięty czas", Run::getRunTimeAsString)
                .printTable();
    }

    public void printScoreboardOfAnEvent() {
        System.out.println("Podaj id eventu:");
        Long eventId = scanner.nextLong();
        RunningEvent runningEvent = runningEventDAO.getEventById(eventId);
        System.out.println("Tablica wyników biegu " + runningEvent + ":");
        new TablePrinter<Run>()
                .withData(runDAO.readAllRunsOfSpecificEvent(runningEvent))
                .withColumn("Miejsce", Run::incrementPlace)
                .withColumn("Dane biegacza", Run::getRunnerAsString)
                .withColumn("Osiągnięty czas", Run::getRunTimeAsString)
                .printTable();
    }

    public void addNewRun() {
        System.out.println("Podaj id biegacza:");
        Long runnerId = scanner.nextLong();
        System.out.println("Podaj id biegu:");
        Long eventId = scanner.nextLong();
//        boolean isFormatCorrect = false;
//        LocalTime bestTime = null;
//        while(!isFormatCorrect) {
//            System.out.println("Podaj najlepszy czas na 10 km (poprawny format HH:MM:SS):");
//            String bestTimeString = s.nextLine();
//            try {
//                bestTime = LocalTime.parse(bestTimeString);
//                isFormatCorrect=true;
//            } catch (Exception e) {
//                System.out.println("Czas został podany w złym formacie!");
//            }

        Runner runner = runnerDAO.getRunnerById(runnerId);
        RunningEvent runningEvent = runningEventDAO.getEventById(eventId);
        Run run = new Run(runner, runningEvent);

        runDAO.create(run);
    }

    public void updateRunTime () {
        System.out.println("Podaj id biegacza:");
        Long runnerId = scanner.nextLong();
        System.out.println("Podaj id biegu:");
        Long eventId = scanner.nextLong();
        scanner.nextLine();
        boolean isFormatCorrect = false;
        LocalTime runTime = null;
        while(!isFormatCorrect) {
            System.out.println("Podaj osiągnięty czas (poprawny format HH:MM:SS):");
            String runTimeString = scanner.nextLine();
            try {
                runTime = LocalTime.parse(runTimeString);
                isFormatCorrect = true;
            } catch (Exception e) {
                System.out.println("Czas został podany w złym formacie!");
            }
        }

        Runner runner = runnerDAO.getRunnerById(runnerId);
        RunningEvent runningEvent = runningEventDAO.getEventById(eventId);

        runDAO.updateRunTime(runner, runningEvent, runTime);
    }

}
