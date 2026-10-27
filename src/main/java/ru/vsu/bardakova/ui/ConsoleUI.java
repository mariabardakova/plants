package ru.vsu.bardakova.ui;

import ru.vsu.bardakova.ex.AppException;
import ru.vsu.bardakova.service.PlantService;
import ru.vsu.bardakova.ui.command.Command;
import ru.vsu.bardakova.ui.command.CommandResult;
import ru.vsu.bardakova.ui.command.ConsoleHelper;
import ru.vsu.bardakova.ui.command.impl.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;


public class ConsoleUI {

    private final ConsoleHelper helper;
    private final Map<String, Command> commands = new HashMap<>();

    public ConsoleUI(PlantService service) {
        this.helper = new ConsoleHelper(new Scanner(System.in));

        List<Command> allCommands = List.of(
                new PlantAddCommand(service),
                new PlantListCommand(service),
                new PlantGetCommand(service),
                new PlantDeleteCommand(service),
                new PlantWaterCommand(service),
                new PlantThirstyCommand(service),
                new ExitCommand()
        );

        for (Command cmd : allCommands) {
            commands.put(cmd.getKey(), cmd);
        }

        Command help = new HelpCommand(commands);
        commands.put(help.getKey(), help);
    }

    public void start() {
        System.out.println("Трекер растений. Введите 'help' для списка команд.");
        boolean running = true;
        while (running) {
            String line = helper.readLine("> ");
            if (line.isBlank()) continue;

            List<String> tokensList = tokenize(line);
            if (tokensList.isEmpty()) continue;

            String matchedKey = null;
            for (String key : commands.keySet()) {
                int words = key.split(" ").length;
                if (tokensList.size() >= words) {
                    String prefix = String.join(" ", tokensList.subList(0, words));
                    if (prefix.equals(key)) {
                        matchedKey = key;
                        break;
                    }
                }
            }

            if (matchedKey == null) {
                System.out.println("Неизвестная команда. Введите 'help'.");
                continue;
            }

            int wordsInKey = matchedKey.split(" ").length;
            String[] args = tokensList.subList(wordsInKey, tokensList.size()).toArray(new String[0]);

            Command command = commands.get(matchedKey);
            try {
                if (command.execute(args) == CommandResult.EXIT) {
                    running = false;
                }
            } catch (AppException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private List<String> tokenize(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (Character.isWhitespace(c) && !inQuotes) {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
            } else {
                current.append(c);
            }
        }
        if (current.length() > 0) tokens.add(current.toString());
        return tokens;
    }
}