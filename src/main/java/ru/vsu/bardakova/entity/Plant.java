package ru.vsu.bardakova.entity;

import ru.vsu.bardakova.ex.ValidationException;

import java.time.LocalDate;
import java.util.UUID;

public class Plant extends AbstractEntity<UUID> {
    private String name;
    private String species;
    private LocalDate lastWatered;
    private int wateringIntervalDays;

    public Plant(UUID id, String name, String species, LocalDate lastWatered, int wateringIntervalDays) {
        super(id);
        validate(name, species, wateringIntervalDays);
        this.name = name.trim();
        this.species = species.trim();
        this.lastWatered = lastWatered;
        this.wateringIntervalDays = wateringIntervalDays;
    }

    public Plant(String name, String species, LocalDate lastWatered, int wateringIntervalDays) {
        this(UUID.randomUUID(), name, species, null, wateringIntervalDays);
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public LocalDate getLastWatered() {
        return lastWatered;
    }

    public int getWateringIntervalDays() {
        return wateringIntervalDays;
    }

    public void setLastWatered(LocalDate lastWatered) {
        this.lastWatered = lastWatered;
    }

    private static void validate(String name, String species, int wateringIntervalDays) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Имя растения не может быть пустым");
        }
        if (species == null || species.isBlank()) {
            throw new ValidationException("Вид растения не может быть пустым");
        }
        if (wateringIntervalDays <= 0) {
            throw new ValidationException("Интервал полива должен быть больше нуля");
        }
    }
}