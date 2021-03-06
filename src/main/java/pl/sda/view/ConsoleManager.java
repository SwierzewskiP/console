package pl.sda.view;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleManager {

    private RunManager runManager = new RunManager();
    private RunnerManager runnerManager = new RunnerManager();
    private RunningEventManager runningEventManager = new RunningEventManager();

    public void start() {
        char userChoice = ' ';
        while (userChoice != 'q') {
            printMenu();
            userChoice = readChar();
            executeAction(userChoice);
        }
    }

    private void printMenu() {
        clrscr();
        System.out.println("Menu:");
        System.out.println("1 - Lista biegaczy");
        System.out.println("2 - Lista wydarzeń biegowych");
        System.out.println();
        System.out.println("3 - Dodaj biegacza");
        System.out.println("4 - Zapisz się na bieg");
        System.out.println("5 - Zarejestruj czas biegu");
        System.out.println("6 - Wyświetl swoje biegi");
        System.out.println();
        System.out.println("7 - Wyświetl wszystkich uczestników danego biegu");
        System.out.println("8 - Wyświetl wyniki danego biegu");
        System.out.println();
        System.out.println("9 - Dodaj nowe wydarzenie biegowe");
        System.out.println();
        System.out.println("a - Usuń biegacza");
        System.out.println("b - Usuń wydarzenie biegowe");
        System.out.println();
        System.out.println("q - wyjście");

    }

    public static void clrscr() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }


    private void executeAction(char userChoice) {
        switch (userChoice) {
            case '1':
                runnerManager.printList();
                pressEnterKeyToContinue();
                break;
            case '2':
                runningEventManager.printList();
                pressEnterKeyToContinue();
                break;
            case '3':
                runnerManager.addRunner();
                pressEnterKeyToContinue();
                break;
            case '4':
                runManager.addNewRun();
                pressEnterKeyToContinue();
                break;
            case '5':
                runManager.updateRunTime();
                pressEnterKeyToContinue();
                break;
            case '6':
                runManager.printAllRunsOfOneRunner();
                pressEnterKeyToContinue();
                break;
            case '7':
                runManager.printAllRunnersOfAnEvent();
                pressEnterKeyToContinue();
                break;
            case '8':
                runManager.printScoreboardOfAnEvent();
                pressEnterKeyToContinue();
                break;
            case '9':
                runningEventManager.addRunningEvent();
                pressEnterKeyToContinue();
                break;
            case 'a':
                runnerManager.deleteRunner();
                pressEnterKeyToContinue();
                break;
            case 'b':
                runningEventManager.deleteRunningEvent();
                pressEnterKeyToContinue();
        }
    }

    public void pressEnterKeyToContinue() {
        System.out.println("Naciśnij Enter, by kontynuować...");
        readChar();
    }

    private char readChar() {
        Scanner s = new Scanner(System.in);
        return (char) s.nextLine().chars().findFirst().orElse(0);

    }
}
