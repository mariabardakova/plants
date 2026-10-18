package ru.vsu.bardakova.ui.command.impl;

import ru.vsu.bardakova.entity.Plant;
import ru.vsu.bardakova.service.PlantService;
import ru.vsu.bardakova.ui.command.Command;
import ru.vsu.bardakova.ui.command.ConsoleHelper;

import java.util.List;

public class PlantListCommand implements Command {

    private final PlantService service;
    private final ConsoleHelper helper;

    public PlantListCommand(PlantService service, ConsoleHelper helper) {
        this.service = service;
        this.helper = helper;
    }

    @Override
    public boolean execute(String[] args) {
        List<Plant> plants = service.getAllPlants();
        helper.printPlants(plants, "Список пуст.");
        return true;
    }

    @Override
    public String description() {
        return "список всех растений";
    }
}