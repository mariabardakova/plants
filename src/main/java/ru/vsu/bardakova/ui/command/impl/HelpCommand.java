package ru.vsu.bardakova.ui.command.impl;

import ru.vsu.bardakova.ui.command.Command;

import java.util.Map;

public class HelpCommand implements Command {

    private final Map<String, Command> commands;

    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public boolean execute(String[] args) {
        System.out.println("Доступные команды:");
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            System.out.printf("  %-20s — %s%n", entry.getKey(), entry.getValue().description());
        }
        return true;
    }

    @Override
    public String description() {
        return "показать список команд";
    }
}
