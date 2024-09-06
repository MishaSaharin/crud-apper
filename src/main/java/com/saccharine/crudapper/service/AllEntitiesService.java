package com.saccharine.crudapper.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AllEntitiesService<T> {
    // create
    T create(final T entity);

    // read one
    Optional<T> getById(final String id);

    // read all
    List<T> getAll();

    // read by name
    Optional<T> getByName(final String name);

    // update
    T update(final T entity);

    // delete
    void deleteById(final String id);
}