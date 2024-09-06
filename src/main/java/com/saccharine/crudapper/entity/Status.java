package com.saccharine.crudapper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "statuses", schema = "sites")
public class Status extends BaseEntity {
    @Column(name = "status", nullable = false, length = 36)
    private String status;

    public Status() {
    }

    public Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String name) {
        this.status = name;
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
        Status that = (Status) o;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), status);
    }

    @Override
    public String toString() {
        return "PostStatus{"
                + "name='" + status + '\''
                + '}';
    }
}