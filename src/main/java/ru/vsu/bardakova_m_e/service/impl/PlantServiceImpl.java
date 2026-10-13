package ru.vsu.bardakova_m_e.service.impl;

import ru.vsu.bardakova_m_e.entity.Plant;
import ru.vsu.bardakova_m_e.repository.PlantRepository;
import ru.vsu.bardakova_m_e.service.PlantService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PlantServiceImpl implements PlantService {

    private final PlantRepository repository;

    public PlantServiceImpl(PlantRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException();
        }
        this.repository = repository;
    }

    @Override
    public Plant addPlant(String name, String species, int wateringIntervalDays) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (species == null || species.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (wateringIntervalDays <= 0) {
            throw new IllegalArgumentException();
        }
        Plant plant = new Plant(name, species, null, wateringIntervalDays);
        repository.save(plant);
        return plant;
    }

    @Override
    public Plant getPlantById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public List<Plant> getAllPlants() {
        return repository.findAll();
    }

    @Override
    public void deletePlant(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException();
        }
        repository.deleteById(id);
    }

    @Override
    public Plant waterPlant(UUID id) {
        Plant plant = getPlantById(id);
        plant.setLastWatered(LocalDate.now());
        repository.save(plant);
        return plant;
    }

    @Override
    public List<Plant> findNeedingWater() {
        return repository.findNeedingWater(LocalDate.now());
    }


}
