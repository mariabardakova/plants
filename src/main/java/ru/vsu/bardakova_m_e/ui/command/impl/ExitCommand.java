package ru.vsu.bardakova_m_e.ui.command.impl;

import ru.vsu.bardakova_m_e.ui.command.Command;

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
