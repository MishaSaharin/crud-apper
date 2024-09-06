package com.saccharine.crudapper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "types", schema = "sites")
public class Type extends BaseEntity {
    @Column(name = "type", nullable = false, length = 36)
    private String type;

    public Type() {
    }

    public Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String name) {
        this.type = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Type type = (Type) o;
        return Objects.equals(type, type.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        return "PostType{" + "name='" + type + '\''
                + '}';
    }
}