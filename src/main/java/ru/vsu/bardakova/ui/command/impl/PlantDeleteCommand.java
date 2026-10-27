package ru.vsu.bardakova.ui.command.impl;

import ru.vsu.bardakova.entity.Plant;
import ru.vsu.bardakova.service.PlantService;
import ru.vsu.bardakova.ui.command.Command;
import ru.vsu.bardakova.ui.command.CommandResult;

public class PlantDeleteCommand implements Command {

    private final PlantService service;

    public PlantDeleteCommand(PlantService service) {
        this.service = service;
    }

    @Override
    public String getKey() {
        return "plant delete";
    }

    @Override
    public CommandResult execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите имя: plant delete \"Имя\"");
            return CommandResult.CONTINUE;
        }
        Plant plant = service.getPlantByName(args[0]);
        service.deletePlant(plant.getId());
        System.out.println("Растение удалено.");
        return CommandResult.CONTINUE;
    }

    @Override
    public String description() {
        return "удалить растение: plant delete \"Имя\"";
    }
}