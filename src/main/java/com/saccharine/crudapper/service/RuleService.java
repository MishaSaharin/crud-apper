package com.saccharine.crudapper.service;

import com.saccharine.crudapper.entity.Rule;
import com.saccharine.crudapper.repository.RuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class RuleService implements AllEntitiesService<Rule> {
    private final RuleRepository ruleRepository;

    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    @Transactional
    public Rule create(Rule entity) {
        Rule newRule = new Rule();
        newRule.setRule(entity.getRule());
        return ruleRepository.save(newRule);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Rule> getById(String id) {
        Optional<Rule> ruleRepositoryById = ruleRepository.findById(id);
        if (ruleRepositoryById.isEmpty()) {
            throw new NoSuchElementException("Rule with id " + id + " not found");
        } else {
            return ruleRepositoryById;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rule> getAll() {
        List<Rule> rulesList = new ArrayList<>();
        ruleRepository.findAll().forEach(rulesList::add);
        if (rulesList.isEmpty()) {
            throw new NoSuchElementException("Object Rule list is empty");
        } else {
            return rulesList;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Rule> getByName(String name) {
        AtomicReference<Rule> ruleAtomicReference = new AtomicReference<>();
        ruleRepository.findAll().forEach(ruleFindAll -> {
            if (ruleFindAll.getRule().equals(name) && ruleFindAll.getRule() != null) {
                ruleAtomicReference.set(ruleFindAll);
            } else {
                throw new NoSuchElementException("Rule with name " + name + " not found");
            }
        });
        return Optional.of(ruleAtomicReference.get());
    }

    @Override
    @Transactional
    public Rule update(Rule entity) {
        Rule updatedRule;
        String entityId = entity.getId();
        Optional<Rule> ruleRepositoryById = ruleRepository.findById(entityId);
        if (ruleRepositoryById.isEmpty()) {
            throw new NoSuchElementException("Rule with id " + entityId + " not found");
        } else {
            updatedRule = ruleRepositoryById.get();
        }
        updatedRule.setRule(entity.getRule());
        return ruleRepository.save(updatedRule);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ruleRepository.deleteById(id);
    }
}