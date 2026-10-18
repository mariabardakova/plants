package ru.vsu.bardakova.ui.command.impl;

import ru.vsu.bardakova.entity.Plant;
import ru.vsu.bardakova.service.PlantService;
import ru.vsu.bardakova.ui.command.Command;
import ru.vsu.bardakova.ui.command.ConsoleHelper;

public class PlantGetCommand implements Command {

    private final PlantService service;
    private final ConsoleHelper helper;

    public PlantGetCommand(PlantService service, ConsoleHelper helper) {
        this.service = service;
        this.helper = helper;
    }

    @Override
    public boolean execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите имя: plant get \"Имя\"");
            return true;
        }
        Plant plant = service.getPlantByName(args[0]);
        helper.printPlant(plant);
        return true;
    }

    @Override
    public String description() {
        return "показать растение: plant get \"Имя\"";
    }
}