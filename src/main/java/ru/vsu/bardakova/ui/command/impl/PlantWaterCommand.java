package ru.vsu.bardakova.ui.command.impl;

import ru.vsu.bardakova.entity.Plant;
import ru.vsu.bardakova.service.PlantService;
import ru.vsu.bardakova.ui.command.Command;
import ru.vsu.bardakova.ui.command.CommandResult;

public class PlantWaterCommand implements Command {

    private final PlantService service;

    public PlantWaterCommand(PlantService service) {
        this.service = service;
    }

    @Override
    public String getKey() {
        return "plant water";
    }

    @Override
    public CommandResult execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите имя: plant water \"Имя\"");
            return CommandResult.CONTINUE;
        }
        Plant plant = service.getPlantByName(args[0]);
        Plant watered = service.waterPlant(plant.getId());
        System.out.println("Растение полито: " + watered.getName()
                + " (полив: " + watered.getLastWatered() + ")");
        return CommandResult.CONTINUE;
    }

    @Override
    public String description() {
        return "полить растение: plant water \"Имя\"";
    }
}