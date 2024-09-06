package com.saccharine.crudapper.service;

import com.saccharine.crudapper.entity.Type;
import com.saccharine.crudapper.repository.PostTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PostTypeService implements AllEntitiesService<Type> {
    private final PostTypeRepository postTypeRepository;

    public PostTypeService(PostTypeRepository postTypeRepository) {
        this.postTypeRepository = postTypeRepository;
    }

    @Override
    @Transactional
    public Type create(Type entity) {
        Type newType = new Type();
        newType.setType(entity.getType());
        return postTypeRepository.save(newType);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Type> getById(String id) {
        Optional<Type> byId = postTypeRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NoSuchElementException("PostType with id " + id + " not found");
        } else {
            return byId;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Type> getAll() {
        List<Type> typesList = new ArrayList<>();
        postTypeRepository.findAll().forEach(typesList::add);
        if (typesList.isEmpty()) {
            throw new NoSuchElementException("Object PostType list is empty");
        } else {
            return typesList;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Type> getByName(String name) {
        AtomicReference<Type> postTypeAtomicReference = new AtomicReference<>();
        postTypeRepository.findAll().forEach(typeFindAll -> {
            if (typeFindAll.getType().equals(name) && typeFindAll.getType() != null) {
                postTypeAtomicReference.set(typeFindAll);
            } else {
                throw new NoSuchElementException("PostType with name " + name + " not found");
            }
        });
        return Optional.of(postTypeAtomicReference.get());
    }

    @Override
    @Transactional
    public Type update(Type entity) {
        String entityId = entity.getId();
        Type updatedType;
        Optional<Type> postTypeRepositoryById = postTypeRepository.findById(entityId);
        if (postTypeRepositoryById.isEmpty()) {
            throw new NoSuchElementException("PostType with id " + entityId + " not found");
        } else {
            updatedType = postTypeRepositoryById.get();
        }
        updatedType.setType(entity.getType());
        return postTypeRepository.save(updatedType);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        postTypeRepository.deleteById(id);
    }
}