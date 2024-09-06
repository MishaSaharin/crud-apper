package com.saccharine.crudapper.repository;

import com.saccharine.crudapper.entity.Rule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends CrudRepository<Rule, String> {
}