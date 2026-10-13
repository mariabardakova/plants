package ru.vsu.bardakova_m_e.ui.command.impl;

import ru.vsu.bardakova_m_e.entity.Plant;
import ru.vsu.bardakova_m_e.service.PlantService;
import ru.vsu.bardakova_m_e.ui.command.Command;
import ru.vsu.bardakova_m_e.ui.command.ConsoleHelper;

public class PlantAddCommand implements Command {

    private final PlantService service;
    private final ConsoleHelper helper;

    public PlantAddCommand(PlantService service, ConsoleHelper helper) {
        this.service = service;
        this.helper = helper;
    }

    @Override
    public boolean execute(String[] args) {
        String name = helper.readNonBlank("Имя: ");
        String species = helper.readNonBlank("Вид: ");
        int interval = helper.readInt("Интервал полива (дней): ");

        Plant plant = service.addPlant(name, species, interval);
        System.out.println("Растение добавлено: " + plant.getName() + " (id: " + plant.getId() + ")");
        return true;
    }

    @Override
    public String description() {
        return "добавить растение";
    }
}

