package ru.vsu.bardakova.repository;

import ru.vsu.bardakova.entity.Plant;

import java.time.LocalDate;
import java.util.List;

public interface PlantRepository extends AbstractRepository<Plant, java.util.UUID> {

    List<Plant> findNeedingWater(LocalDate today);

    List<Plant> findByName(String name);

}