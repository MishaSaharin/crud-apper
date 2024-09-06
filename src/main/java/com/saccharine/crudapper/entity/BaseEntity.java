package com.saccharine.crudapper.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @Column(unique = true, nullable = false, updatable = false, length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private final String id = UUID.randomUUID().toString().trim().replaceAll("-", "");

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BaseEntity{"
                + "id='" + id + '\''
                + '}';
    }
}