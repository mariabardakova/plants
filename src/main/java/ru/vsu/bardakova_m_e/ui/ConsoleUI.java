package ru.vsu.bardakova_m_e.ui;

import ru.vsu.bardakova_m_e.service.PlantService;
import ru.vsu.bardakova_m_e.ui.command.Command;
import ru.vsu.bardakova_m_e.ui.command.ConsoleHelper;
import ru.vsu.bardakova_m_e.ui.command.impl.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUI {

    private final ConsoleHelper helper;
    private final Map<String, Command> commands = new HashMap<>();

    public ConsoleUI(PlantService service) {
        this.helper = new ConsoleHelper(new Scanner(System.in));

        commands.put("plant add", new PlantAddCommand(service, helper));
        commands.put("plant list", new PlantListCommand(service, helper));
        commands.put("plant get", new PlantGetCommand(service, helper));
        commands.put("plant delete", new PlantDeleteCommand(service, helper));
        commands.put("plant water", new PlantWaterCommand(service, helper));
        commands.put("plant thirsty", new PlantThirstyCommand(service, helper));
        commands.put("exit", new ExitCommand());
        commands.put("help", new HelpCommand(commands));
    }

    public void start() {
        System.out.println("Трекер растений. Введите 'help' для списка команд.");
        boolean running = true;
        while (running) {
            String line = helper.readLine("> ");
            if (line.isBlank()) {
                continue;
            }

            List<String> tokensList = tokenize(line);
            if (tokensList.isEmpty()) {
                continue;
            }
            String[] tokens = tokensList.toArray(new String[0]);

            String key = findKey(tokens);
            if (key == null) {
                System.out.println("Неизвестная команда. Введите 'help'.");
                continue;
            }

            String[] args = new String[tokens.length - key.split(" ").length];
            System.arraycopy(tokens, key.split(" ").length, args, 0, args.length);

            Command command = commands.get(key);
            try {
                running = command.execute(args);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private String findKey(String[] tokens) {
        if (tokens.length >= 2) {
            String two = tokens[0] + " " + tokens[1];
            if (commands.containsKey(two)) {
                return two;
            }
        }
        if (commands.containsKey(tokens[0])) {
            return tokens[0];
        }
        return null;
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

        if (current.length() > 0) {
            tokens.add(current.toString());
        }
        return tokens;
    }
}