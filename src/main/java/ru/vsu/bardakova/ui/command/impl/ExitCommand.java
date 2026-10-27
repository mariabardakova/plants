package ru.vsu.bardakova.ui.command.impl;

import ru.vsu.bardakova.ui.command.Command;
import ru.vsu.bardakova.ui.command.CommandResult;

public class ExitCommand implements Command {

    @Override
    public String getKey() {
        return "exit";
    }

    @Override
    public CommandResult execute(String[] args) {
        System.out.println("До свидания!");
        return CommandResult.EXIT;
    }

    @Override
    public String description() {
        return "выход";
    }
}
