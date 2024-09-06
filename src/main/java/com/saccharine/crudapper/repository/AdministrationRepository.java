package com.saccharine.crudapper.repository;

import com.saccharine.crudapper.entity.Administration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrationRepository extends CrudRepository<Administration, String> {
}