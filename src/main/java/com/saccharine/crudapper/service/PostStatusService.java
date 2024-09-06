package com.saccharine.crudapper.service;

import com.saccharine.crudapper.entity.Status;
import com.saccharine.crudapper.repository.PostStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PostStatusService implements AllEntitiesService<Status> {
    private final PostStatusRepository postStatusRepository;

    public PostStatusService(PostStatusRepository postStatusRepository) {
        this.postStatusRepository = postStatusRepository;
    }

    @Override
    @Transactional
    public Status create(Status entity) {
        Status newStatus = new Status();
        newStatus.setStatus(entity.getStatus());
        return postStatusRepository.save(newStatus);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Status> getById(String id) {
        Optional<Status> postStatusRepositoryById = postStatusRepository.findById(id);
        if (postStatusRepositoryById.isEmpty()) {
            throw new NoSuchElementException("Post status with id " + id + " not found");
        } else {
            return postStatusRepositoryById;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Status> getAll() {
        List<Status> postsStatusList = new ArrayList<>();
        postStatusRepository.findAll().forEach(postsStatusList::add);
        if (postsStatusList.isEmpty()) {
            throw new NoSuchElementException("Post status list is empty");
        } else {
            return postsStatusList;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Status> getByName(String name) {
        AtomicReference<Status> postStatus = new AtomicReference<>();
        postStatusRepository.findAll().forEach(statusFindAll -> {
            if (statusFindAll.getStatus().equals(name) && statusFindAll.getStatus() != null) {
                postStatus.set(statusFindAll);
            } else {
                throw new NoSuchElementException("Post status with name " + name + " not found");
            }
        });
        return Optional.of(postStatus.get());

    }

    @Override
    @Transactional
    public Status update(Status entity) {
        Status newStatus;
        String entityId = entity.getId();
        Optional<Status> postStatusRepositoryById = postStatusRepository.findById(entityId);
        if (postStatusRepositoryById.isEmpty()) {
            throw new NoSuchElementException("Post status with id " + entityId + " not found");
        } else {
            newStatus = postStatusRepositoryById.get();
        }
        newStatus.setStatus(entity.getStatus());
        return postStatusRepository.save(newStatus);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        postStatusRepository.deleteById(id);
    }
}