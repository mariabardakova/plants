package ru.vsu.bardakova.repository.impl;

import ru.vsu.bardakova.config.DataSourceProvider;
import ru.vsu.bardakova.entity.Plant;
import ru.vsu.bardakova.ex.RepositoryException;
import ru.vsu.bardakova.repository.PlantRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JdbcPlantRepository implements PlantRepository {

    @Override
    public void save(Plant plant) {
        if (plant == null) throw new RepositoryException("Нельзя сохранить пустое растение");

        boolean exists = findById(plant.getId()).isPresent();

        String sql = exists
                ? "UPDATE plants SET name=?, species=?, last_watered=?, watering_interval_days=? WHERE id=?"
                : "INSERT INTO plants (name, species, last_watered, watering_interval_days, id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataSourceProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, plant.getName());
            ps.setString(2, plant.getSpecies());

            if (plant.getLastWatered() == null) {
                ps.setNull(3, Types.DATE);
            } else {
                ps.setDate(3, Date.valueOf(plant.getLastWatered()));
            }

            ps.setInt(4, plant.getWateringIntervalDays());
            ps.setObject(5, plant.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Ошибка при сохранении растения: " + e.getMessage());
        }
    }

    @Override
    public void delete(Plant plant) {
        if (plant != null) deleteById(plant.getId());
    }

    @Override
    public void deleteById(UUID id) {
        String sql = "DELETE FROM plants WHERE id = ?";
        try (Connection conn = DataSourceProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Ошибка при удалении растения: " + e.getMessage());
        }
    }

    @Override
    public Optional<Plant> findById(UUID id) {
        String sql = "SELECT * FROM plants WHERE id = ?";
        try (Connection conn = DataSourceProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToPlant(rs));
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Ошибка при поиске растения по ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Plant> findAll() {
        String sql = "SELECT * FROM plants ORDER BY name";
        List<Plant> result = new ArrayList<>();
        try (Connection conn = DataSourceProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(mapRowToPlant(rs));
            }
        } catch (SQLException e) {
            throw new RepositoryException("Ошибка при получении списка растений: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean existsById(UUID id) {
        return findById(id).isPresent();
    }

    @Override
    public List<Plant> findByName(String name) {
        String sql = "SELECT * FROM plants WHERE LOWER(name) = LOWER(?)";
        List<Plant> result = new ArrayList<>();
        try (Connection conn = DataSourceProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapRowToPlant(rs));
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Ошибка при поиске по имени: " + e.getMessage());
        }
        return result;
    }

    @Override
    public List<Plant> findNeedingWater(LocalDate today) {
        String sql = "SELECT * FROM plants WHERE last_watered IS NULL " +
                "OR last_watered + watering_interval_days <= ?";
        List<Plant> result = new ArrayList<>();
        try (Connection conn = DataSourceProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(today));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapRowToPlant(rs));
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Ошибка при поиске растений для полива: " + e.getMessage());
        }
        return result;
    }

    private Plant mapRowToPlant(ResultSet rs) throws SQLException {
        UUID id = rs.getObject("id", UUID.class);
        String name = rs.getString("name");
        String species = rs.getString("species");

        Date lastWateredSql = rs.getDate("last_watered");
        LocalDate lastWatered = lastWateredSql != null ? lastWateredSql.toLocalDate() : null;

        int interval = rs.getInt("watering_interval_days");

        return new Plant(id, name, species, lastWatered, interval);
    }
}