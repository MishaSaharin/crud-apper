package com.saccharine.crudapper.repository;

import com.saccharine.crudapper.entity.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTypeRepository extends CrudRepository<Type, String> {
}