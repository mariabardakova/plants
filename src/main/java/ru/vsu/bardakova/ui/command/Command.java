package ru.vsu.bardakova.ui.command;

public interface Command {

    boolean execute(String[] args);

    String description();
}
