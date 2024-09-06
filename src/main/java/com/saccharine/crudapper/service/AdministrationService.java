package com.saccharine.crudapper.service;

import com.saccharine.crudapper.entity.Administration;
import com.saccharine.crudapper.repository.AdministrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AdministrationService implements AllEntitiesService<Administration> {
    private final AdministrationRepository administrationRepository;

    public AdministrationService(AdministrationRepository administrationRepository) {
        this.administrationRepository = administrationRepository;
    }

    @Override
    @Transactional
    public Administration create(Administration entity) {
        Administration newAdministration = new Administration();
        newAdministration.setAdministration(entity.getAdministration());
        return administrationRepository.save(newAdministration);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Administration> getById(String id) {
        Optional<Administration> administrationRepositoryById = administrationRepository.findById(id);
        if (administrationRepositoryById.isEmpty()) {
            throw new NoSuchElementException("Object administration with id " + id + " not found");
        } else {
            return administrationRepositoryById;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Administration> getAll() {
        List<Administration> administrationsList = new ArrayList<>();
        administrationRepository.findAll().forEach(administrationsList::add);
        if (administrationsList.isEmpty()) {
            throw new NoSuchElementException("Object administration list is empty");
        } else {
            return administrationsList;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Administration> getByName(String name) {
        AtomicReference<Administration> administrationAtomicReference = new AtomicReference<>();
        administrationRepository.findAll().forEach(administrationFindAll -> {
            String string = administrationFindAll.getAdministration();
            if (string.equals(name)) {
                administrationAtomicReference.set(administrationFindAll);
            }
        });
        if (administrationAtomicReference.get() == null) {
            throw new NoSuchElementException("Object administration with name " + name + " not found");
        }
        return Optional.of(administrationAtomicReference.get());
    }

    @Override
    @Transactional
    public Administration update(Administration entity) {
        Administration updatedAdministration;
        String entityId = entity.getId();
        Optional<Administration> administrationRepositoryById = administrationRepository.findById(entityId);
        if (administrationRepositoryById.isEmpty()) {
            throw new NoSuchElementException("Object administration with id " + entityId + " not found");
        } else {
            updatedAdministration = administrationRepositoryById.get();
        }
        return administrationRepository.save(updatedAdministration);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        administrationRepository.deleteById(id);
    }
}