package ru.vsu.bardakova.ui.command.impl;

import ru.vsu.bardakova.entity.Plant;
import ru.vsu.bardakova.service.PlantService;
import ru.vsu.bardakova.ui.command.Command;
import ru.vsu.bardakova.ui.command.CommandResult;
import ru.vsu.bardakova.ui.view.PlantPrinter;

public class PlantGetCommand implements Command {

    private final PlantService service;

    public PlantGetCommand(PlantService service) {
        this.service = service;
    }

    @Override
    public String getKey() {
        return "plant get";
    }

    @Override
    public CommandResult execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите имя: plant get \"Имя\"");
            return CommandResult.CONTINUE;
        }
        Plant plant = service.getPlantByName(args[0]);
        PlantPrinter.printPlant(plant);
        return CommandResult.CONTINUE;
    }

    @Override
    public String description() {
        return "показать растение: plant get \"Имя\"";
    }
}