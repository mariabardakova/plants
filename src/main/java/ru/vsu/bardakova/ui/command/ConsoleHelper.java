package ru.vsu.bardakova.ui.command;

import ru.vsu.bardakova.entity.Plant;

import java.util.List;
import java.util.Scanner;

public class ConsoleHelper {

    public static final String SEPARATOR = "-".repeat(60);

    private final Scanner scanner;

    public ConsoleHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readLine(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public void printPlants(List<Plant> plants, String emptyMessage) {
        if (plants.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        System.out.printf("%-4s %-20s %-20s %-12s %s%n",
                "№", "Имя", "Вид", "Полив", "Интервал");
        System.out.println(SEPARATOR);
        for (int i = 0; i < plants.size(); i++) {
            Plant p = plants.get(i);
            String watered = p.getLastWatered() == null
                    ? "не полито"
                    : p.getLastWatered().toString();
            System.out.printf("%-4d %-20s %-20s %-12s %d%n",
                    i + 1,
                    p.getName(),
                    p.getSpecies(),
                    watered,
                    p.getWateringIntervalDays());
        }
    }

    public void printPlant(Plant p) {
        System.out.println("Id:        " + p.getId());
        System.out.println("Имя:       " + p.getName());
        System.out.println("Вид:       " + p.getSpecies());
        System.out.println("Полив:     " + (p.getLastWatered() == null ? "не полито" : p.getLastWatered()));
        System.out.println("Интервал:  " + p.getWateringIntervalDays() + " дн.");
    }

}
