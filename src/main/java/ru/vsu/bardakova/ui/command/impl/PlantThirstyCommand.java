package ru.vsu.bardakova.ui.command.impl;

import ru.vsu.bardakova.entity.Plant;
import ru.vsu.bardakova.service.PlantService;
import ru.vsu.bardakova.ui.command.Command;
import ru.vsu.bardakova.ui.command.CommandResult;
import ru.vsu.bardakova.ui.view.PlantPrinter;

import java.util.List;

public class PlantThirstyCommand implements Command {

    private final PlantService service;

    public PlantThirstyCommand(PlantService service) {
        this.service = service;
    }

    @Override
    public String getKey() {
        return "plant thirsty";
    }

    @Override
    public CommandResult execute(String[] args) {
        List<Plant> plants = service.findNeedingWater();
        PlantPrinter.printPlants(plants, "Все растения политы.");
        return CommandResult.CONTINUE;
    }

    @Override
    public String description() {
        return "показать растения, которые пора поливать";
    }
}