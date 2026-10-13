package ru.vsu.bardakova_m_e.ui.command;

public interface Command {

    boolean execute(String[] args);

    String description();
}
