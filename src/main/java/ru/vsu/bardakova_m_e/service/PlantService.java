package ru.vsu.bardakova_m_e.service;

import ru.vsu.bardakova_m_e.entity.Plant;

import java.util.List;
import java.util.UUID;

public interface PlantService {

    Plant addPlant(String name, String species, int wateringIntervalDay);

    Plant getPlantById(UUID id);

    List<Plant> getAllPlants();

    void deletePlant(UUID id);

    Plant waterPlant(UUID id);

    List<Plant> findNeedingWater();
}
