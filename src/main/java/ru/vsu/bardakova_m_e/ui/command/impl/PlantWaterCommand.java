package ru.vsu.bardakova_m_e.ui.command.impl;

import ru.vsu.bardakova_m_e.entity.Plant;
import ru.vsu.bardakova_m_e.service.PlantService;
import ru.vsu.bardakova_m_e.ui.command.Command;
import ru.vsu.bardakova_m_e.ui.command.ConsoleHelper;

import java.util.UUID;

public class PlantWaterCommand implements Command {

    private final PlantService service;
    private final ConsoleHelper helper;

    public PlantWaterCommand(PlantService service, ConsoleHelper helper) {
        this.service = service;
        this.helper = helper;
    }

    @Override
    public boolean execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите id: plant water <id>");
            return true;
        }
        UUID id;
        try {
            id = UUID.fromString(args[0]);
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный формат id.");
            return true;
        }
        Plant plant = service.waterPlant(id);
        System.out.println("Растение полито: " + plant.getName()
                + " (полив: " + plant.getLastWatered() + ")");
        return true;
    }

    @Override
    public String description() {
        return "полить растение: plant water <id>";
    }
}