package ru.vsu.bardakova_m_e.ui.command.impl;

import ru.vsu.bardakova_m_e.entity.Plant;
import ru.vsu.bardakova_m_e.service.PlantService;
import ru.vsu.bardakova_m_e.ui.command.Command;
import ru.vsu.bardakova_m_e.ui.command.ConsoleHelper;

import java.util.List;

public class PlantThirstyCommand implements Command {

    private final PlantService service;
    private final ConsoleHelper helper;

    public PlantThirstyCommand(PlantService service, ConsoleHelper helper) {
        this.service = service;
        this.helper = helper;
    }

    @Override
    public boolean execute(String[] args) {
        List<Plant> plants = service.findNeedingWater();
        helper.printPlants(plants, "Все растения политы.");
        return true;
    }

    @Override
    public String description() {
        return "показать растения, которые пора поливать";
    }
}