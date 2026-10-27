package ru.vsu.bardakova.ui.command;

public interface Command {

    CommandResult execute(String[] args);

    String description();

    String getKey();
}
