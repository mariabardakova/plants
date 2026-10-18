package ru.vsu.bardakova_m_e.ui.command.impl;

import ru.vsu.bardakova_m_e.entity.Plant;
import ru.vsu.bardakova_m_e.service.PlantService;
import ru.vsu.bardakova_m_e.ui.command.Command;
import ru.vsu.bardakova_m_e.ui.command.ConsoleHelper;

public class PlantAddCommand implements Command {

    private final PlantService service;
    private final ConsoleHelper helper;

    public PlantAddCommand(PlantService service, ConsoleHelper helper) {
        this.service = service;
        this.helper = helper;
    }

    @Override
    public boolean execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Формат: plant add \"Имя\" \"Вид\" <Интервал>");
            return true;
        }

        String name = args[0];
        String species = args[1];

        int interval;
        try {
            interval = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: интервал должен быть числом.");
            return true;
        }

        Plant plant = service.addPlant(name, species, interval);
        System.out.println("Растение добавлено: " + plant.getName() + " (id: " + plant.getId() + ")");
        return true;
    }

    @Override
    public String description() {
        return "добавить растение: plant add \"Имя\" \"Вид\" <Интервал>";
    }
}

