package ru.vsu.bardakova_m_e.repository.impl;

import ru.vsu.bardakova_m_e.entity.Plant;
import ru.vsu.bardakova_m_e.repository.PlantRepository;

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
            throw new IllegalArgumentException();
        }
        storage.put(plant.getId(), plant);
    }

    @Override
    public void delete(Plant plant) {
        if (plant == null) {
            return;
        }
        deleteById(plant.getId());
    }

    @Override
    public void deleteById(UUID id) {
        if (id == null) {
            return;
        }
        storage.remove(id);
    }

    @Override
    public Optional<Plant> findById(UUID id) {
        if (id == null) {
            return Optional.empty();
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
            return false;
        }
        return storage.containsKey(id);
    }

    @Override
    public List<Plant> findNeedingWater(LocalDate today) {
        if (today == null) {
            throw new IllegalArgumentException();
        }
        List<Plant> result = new ArrayList<>();
        for (Plant plant : storage.values()) {
            if (needsWatering(plant, today)) {
                result.add(plant);
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

    @Override
    public List<Plant> findByName(String name) {
        if (name == null || name.isBlank()) {
            return List.of();
        }
        List<Plant> result = new ArrayList<>();
        for (Plant p : storage.values()) {
            if (p.getName().equalsIgnoreCase(name.trim())) {
                result.add(p);
            }
        }
        return result;
    }

}
