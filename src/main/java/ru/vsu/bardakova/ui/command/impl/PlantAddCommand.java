package ru.vsu.bardakova.ui.command.impl;

import ru.vsu.bardakova.entity.Plant;
import ru.vsu.bardakova.ex.ValidationException;
import ru.vsu.bardakova.service.PlantService;
import ru.vsu.bardakova.ui.command.Command;

public class PlantAddCommand implements Command {

    private final PlantService service;

    public PlantAddCommand(PlantService service) {
        this.service = service;
    }

    @Override
    public boolean execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Формат: plant add \"Имя\" \"Вид\" <Интервал>");
            return true;
        }

        String name = args[0];
        String species = args[1];
        String intervalStr = args[2];

        if (!intervalStr.matches("\\d+")) {
            throw new ValidationException("Интервал должен быть положительным числом.");
        }

        int interval = Integer.parseInt(intervalStr);

        Plant plant = service.addPlant(name, species, interval);
        System.out.println("Растение добавлено: " + plant.getName() + " (id: " + plant.getId() + ")");
        return true;
    }

    @Override
    public String description() {
        return "добавить растение: plant add \"Имя\" \"Вид\" <Интервал>";
    }
}