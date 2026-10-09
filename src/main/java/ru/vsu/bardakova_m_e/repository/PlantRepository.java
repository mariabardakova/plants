package ru.vsu.bardakova_m_e.repository;

import ru.vsu.bardakova_m_e.entity.Plant;

import java.time.LocalDate;
import java.util.List;

public interface PlantRepository extends AbstractRepository<Plant, java.util.UUID> {

    List<Plant> findNeedingWater(LocalDate today);

}