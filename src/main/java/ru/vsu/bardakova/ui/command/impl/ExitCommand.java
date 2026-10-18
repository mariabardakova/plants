package ru.vsu.bardakova.ui.command.impl;

import ru.vsu.bardakova.ui.command.Command;

public class ExitCommand implements Command {

    @Override
    public boolean execute(String[] args) {
        System.out.println("До свидания!");
        return false;
    }

    @Override
    public String description() {
        return "выход";
    }
}
