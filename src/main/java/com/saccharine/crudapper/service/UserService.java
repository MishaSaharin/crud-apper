package com.saccharine.crudapper.service;

import com.saccharine.crudapper.entity.User;
import com.saccharine.crudapper.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserService implements AllEntitiesService<User> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User create(User entity) {
        User newUser = new User();
        newUser.setAdministration(entity.getAdministration());
        newUser.setUsername(entity.getUsername());
        newUser.setPassword(entity.getPassword());
        newUser.setEnabled(entity.isEnabled());
        return userRepository.save(newUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getById(String id) {
        Optional<User> userRepositoryById = userRepository.findById(id);
        if (userRepositoryById.isEmpty()) {
            throw new NoSuchElementException("User with id " + id + " not found");
        } else {
            return userRepositoryById;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        List<User> usersList = new ArrayList<>();
        userRepository.findAll().forEach(usersList::add);
        if (usersList.isEmpty()) {
            throw new NoSuchElementException("User list is empty");
        } else {
            return usersList;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getByName(String name) {
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userRepository.findAll().forEach(userFindAll -> {
            if (userFindAll.getUsername().equals(name) && userFindAll.getUsername() != null) {
                userAtomicReference.set(userFindAll);
            } else {
                throw new NoSuchElementException("User with name " + name + " not found");
            }
        });
        return Optional.of(userAtomicReference.get());
    }

    @Override
    @Transactional
    public User update(User entity) {
        String entityId = entity.getId();
        User userUpdated;
        Optional<User> userRepositoryById = userRepository.findById(entityId);
        if (userRepositoryById.isEmpty()) {
            throw new NoSuchElementException("User with id " + entityId + " not found");
        } else {
            userUpdated = userRepositoryById.get();
        }
        userUpdated.setUsername(entity.getUsername());
        userUpdated.setPassword(entity.getPassword());
        userUpdated.setEnabled(entity.isEnabled());
        userUpdated.setAdministration(entity.getAdministration());
        return userRepository.save(userUpdated);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}