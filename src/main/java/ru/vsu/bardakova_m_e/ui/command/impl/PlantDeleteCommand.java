package ru.vsu.bardakova_m_e.ui.command.impl;

import ru.vsu.bardakova_m_e.service.PlantService;
import ru.vsu.bardakova_m_e.ui.command.Command;
import ru.vsu.bardakova_m_e.ui.command.ConsoleHelper;

import java.util.UUID;

public class PlantDeleteCommand implements Command {

    private final PlantService service;
    private final ConsoleHelper helper;

    public PlantDeleteCommand(PlantService service, ConsoleHelper helper) {
        this.service = service;
        this.helper = helper;
    }

    @Override
    public boolean execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите id: plant delete <id>");
            return true;
        }
        UUID id;
        try {
            id = UUID.fromString(args[0]);
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный формат id.");
            return true;
        }
        service.deletePlant(id);
        System.out.println("Растение удалено.");
        return true;
    }

    @Override
    public String description() {
        return "удалить растение: plant delete <id>";
    }
}