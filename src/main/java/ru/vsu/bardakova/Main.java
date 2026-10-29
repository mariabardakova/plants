package ru.vsu.bardakova;

import ru.vsu.bardakova.config.DatabaseMigrator;
import ru.vsu.bardakova.repository.PlantRepository;
import ru.vsu.bardakova.repository.impl.InMemoryPlantRepository;
import ru.vsu.bardakova.repository.impl.JdbcPlantRepository;
import ru.vsu.bardakova.service.PlantService;
import ru.vsu.bardakova.service.impl.PlantServiceImpl;
import ru.vsu.bardakova.ui.ConsoleUI;

public class Main {
    public static void main(String[] args){

        DatabaseMigrator.migrate();
        PlantRepository repository = new JdbcPlantRepository();
        PlantService service = new PlantServiceImpl(repository);
        ConsoleUI ui = new ConsoleUI(service);
        ui.start();
    }
}
