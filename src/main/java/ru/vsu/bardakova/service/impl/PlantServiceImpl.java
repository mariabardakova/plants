package ru.vsu.bardakova.service.impl;

import ru.vsu.bardakova.entity.Plant;
import ru.vsu.bardakova.ex.EntityNotFoundException;
import ru.vsu.bardakova.ex.ValidationException;
import ru.vsu.bardakova.repository.PlantRepository;
import ru.vsu.bardakova.service.PlantService;
import ru.vsu.bardakova.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PlantServiceImpl implements PlantService {

    private final PlantRepository repository;

    public PlantServiceImpl(PlantRepository repository) {
        if (repository == null) {
            throw new ValidationException("Репозиторий не может быть null");
        }
        this.repository = repository;
    }

    @Override
    public Plant addPlant(String name, String species, int wateringIntervalDays) {
        if (StringUtils.isNullOrBlank(name)) {
            throw new ValidationException("Название не может быть пустым");
        }
        if (StringUtils.isNullOrBlank(species)) {
            throw new ValidationException("Вид не может быть пустым");
        }
        if (wateringIntervalDays <= 0) {
            throw new ValidationException("Интервал полива должен быть больше нуля");
        }

        List<Plant> existing = repository.findByName(name);
        if (!existing.isEmpty()) {
            throw new ValidationException("Растение с именем '" + name + "' уже существует. Придумайте другое имя.");
        }

        Plant plant = new Plant(name, species, null, wateringIntervalDays);
        repository.save(plant);
        return plant;
    }

    @Override
    public Plant getPlantById(UUID id) {
        if (id == null) {
            throw new ValidationException("Id не может быть null");
        }
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Растение с id " + id + " не найдено"));
    }

    @Override
    public List<Plant> getAllPlants() {
        return repository.findAll();
    }

    @Override
    public void deletePlant(UUID id) {
        if (id == null) {
            throw new ValidationException("Id не может быть null");
        }
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Растение с id " + id + " не найдено");
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

    @Override
    public Plant getPlantByName(String name) {
        if (StringUtils.isNullOrBlank(name)) {
            throw new ValidationException("Имя не может быть пустым");
        }
        List<Plant> found = repository.findByName(name);
        if (found.isEmpty()) {
            throw new EntityNotFoundException("Растение с именем '" + name + "' не найдено");
        }
        return found.get(0);
    }
}