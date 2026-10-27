package ru.vsu.bardakova.ui.command.impl;

import ru.vsu.bardakova.entity.Plant;
import ru.vsu.bardakova.service.PlantService;
import ru.vsu.bardakova.ui.command.Command;
import ru.vsu.bardakova.ui.command.CommandResult;
import ru.vsu.bardakova.ui.view.PlantPrinter;

import java.util.List;

public class PlantListCommand implements Command {

    private final PlantService service;

    public PlantListCommand(PlantService service) {
        this.service = service;
    }

    @Override
    public String getKey() {
        return "plant list";
    }

    @Override
    public CommandResult execute(String[] args) {
        List<Plant> plants = service.getAllPlants();
        PlantPrinter.printPlants(plants, "Список пуст.");
        return CommandResult.CONTINUE;
    }

    @Override
    public String description() {
        return "список всех растений";
    }
}