package com.saccharine.crudapper.service;

import com.saccharine.crudapper.entity.Post;
import com.saccharine.crudapper.entity.Rule;
import com.saccharine.crudapper.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PostService implements AllEntitiesService<Post> {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public Post create(Post entity) {
        Post newPost = new Post();
        newPost.setType(entity.getType());
        newPost.setRules(entity.getRules());
        newPost.setName(entity.getName());
        newPost.setPhoto(entity.getPhoto());
        newPost.setStatus(entity.getStatus());
        newPost.setAddress(entity.getAddress());
        newPost.setCreatedAt(entity.getCreatedAt()); // LocalDateTime.now()
        newPost.setCarNumber(entity.getCarNumber());
        newPost.setDescription(entity.getDescription());
        return postRepository.save(newPost);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Post> getById(String id) {
        Optional<Post> postRepositoryById = postRepository.findById(id);
        if (postRepositoryById.isEmpty()) {
            throw new NoSuchElementException("Object Post with id " + id + " not found");
        } else {
            return postRepositoryById;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAll() {
        List<Post> postList = new ArrayList<>();
        postRepository.findAll().forEach(postList::add);
        if (postList.isEmpty()) {
            throw new NoSuchElementException("Object Post list is empty");
        } else {
            return postList;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Post> getByName(String name) {
        AtomicReference<Post> postAtomicReference = new AtomicReference<>();
        postRepository.findAll().forEach(postFindAll -> {
            String postFindAllName = postFindAll.getName();
            if (postFindAllName.equals(name)) {
                postAtomicReference.set(postFindAll);
            }
        });
        if (postAtomicReference.get() == null) {
            throw new NoSuchElementException("Object Post with name " + name + " not found");
        }
        return Optional.of(postAtomicReference.get());
    }

    @Override
    @Transactional
    public Post update(Post entity) {
        Post newPost;
        String entityId = entity.getId();
        Optional<Post> postOptional = getById(entityId);
        if (postOptional.isEmpty()) {
            throw new NoSuchElementException("Object Post with id " + entityId + " not found");
        } else {
            newPost = postOptional.get();
        }
        newPost.setName(entity.getName());
        return postRepository.save(newPost);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void updatePostFields(Post entity, Set<Rule> ruleSet, byte[] photo) {
        entity.setPhoto(photo);
        entity.setRules(ruleSet);
        entity.setPhoto(photo);
        postRepository.save(entity);
    }
}