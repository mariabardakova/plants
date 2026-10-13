package ru.vsu.bardakova_m_e;

import ru.vsu.bardakova_m_e.repository.PlantRepository;
import ru.vsu.bardakova_m_e.repository.impl.InMemoryPlantRepository;
import ru.vsu.bardakova_m_e.service.PlantService;
import ru.vsu.bardakova_m_e.service.impl.PlantServiceImpl;
import ru.vsu.bardakova_m_e.ui.ConsoleUI;

public class Main {
    public static void main(String[] args){
        PlantRepository repository = new InMemoryPlantRepository();
        PlantService service = new PlantServiceImpl(repository);
        ConsoleUI ui = new ConsoleUI(service);
        ui.start();

    }
}
