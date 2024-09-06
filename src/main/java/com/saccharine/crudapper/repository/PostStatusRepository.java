package com.saccharine.crudapper.repository;

import com.saccharine.crudapper.entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostStatusRepository extends CrudRepository<Status, String> {
}