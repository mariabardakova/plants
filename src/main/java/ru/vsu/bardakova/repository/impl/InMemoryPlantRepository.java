package ru.vsu.bardakova.repository.impl;

import ru.vsu.bardakova.entity.Plant;
import ru.vsu.bardakova.ex.RepositoryException;
import ru.vsu.bardakova.repository.PlantRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryPlantRepository implements PlantRepository {

    private final Map<UUID, Plant> storage = new HashMap<>();

    @Override
    public void save(Plant plant) {
        if (plant == null) {
            throw new RepositoryException("Нельзя сохранить пустое растение (null)");
        }
        storage.put(plant.getId(), plant);
    }

    @Override
    public void delete(Plant plant) {
        if (plant == null) {
            throw new RepositoryException("Нельзя удалить пустое растение (null)");
        }
        deleteById(plant.getId());
    }

    @Override
    public void deleteById(UUID id) {
        if (id == null) {
            throw new RepositoryException("Id для удаления не может быть null");
        }
        storage.remove(id);
    }

    @Override
    public Optional<Plant> findById(UUID id) {
        if (id == null) {
            throw new RepositoryException("Id для поиска не может быть null");
        }
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Plant> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean existsById(UUID id) {
        if (id == null) {
            throw new RepositoryException("Id для проверки не может быть null");
        }
        return storage.containsKey(id);
    }

    @Override
    public List<Plant> findNeedingWater(LocalDate today) {
        if (today == null) {
            throw new RepositoryException("Дата не может быть null");
        }
        List<Plant> result = new ArrayList<>();
        for (Plant plant : storage.values()) {
            if (needsWatering(plant, today)) {
                result.add(plant);
            }
        }
        return result;
    }

    @Override
    public List<Plant> findByName(String name) {
        if (name == null || name.isBlank()) {
            throw new RepositoryException("Имя для поиска не может быть пустым");
        }
        List<Plant> result = new ArrayList<>();
        for (Plant p : storage.values()) {
            if (p.getName().equalsIgnoreCase(name.trim())) {
                result.add(p);
            }
        }
        return result;
    }

    private boolean needsWatering(Plant plant, LocalDate today) {
        LocalDate last = plant.getLastWatered();
        if (last == null) {
            return true;
        }
        LocalDate next = last.plusDays(plant.getWateringIntervalDays());
        return !next.isAfter(today);
    }
}