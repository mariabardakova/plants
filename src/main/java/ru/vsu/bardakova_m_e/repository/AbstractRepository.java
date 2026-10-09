package ru.vsu.bardakova_m_e.repository;

import ru.vsu.bardakova_m_e.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository<E extends AbstractEntity<ID>, ID> {
    void save(E entity);

    void delete(E entity);

    void deleteById(ID id);

    Optional<E> findById(ID id);

    List<E> findAll();

    boolean existsById(ID id);
}