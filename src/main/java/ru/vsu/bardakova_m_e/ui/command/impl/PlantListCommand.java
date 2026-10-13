package ru.vsu.bardakova_m_e.ui.command.impl;

import ru.vsu.bardakova_m_e.entity.Plant;
import ru.vsu.bardakova_m_e.service.PlantService;
import ru.vsu.bardakova_m_e.ui.command.Command;
import ru.vsu.bardakova_m_e.ui.command.ConsoleHelper;

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