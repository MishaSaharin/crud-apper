package com.saccharine.crudapper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "administrations", schema = "sites")
public class Administration extends BaseEntity {
    @Column(name = "administration", length = 36)
    private String administration;

    public Administration() {
    }

    public Administration(String administration) {
        this.administration = administration;
    }

    public String getAdministration() {
        return administration;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
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
        Administration that = (Administration) o;
        return Objects.equals(administration, that.administration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), administration);
    }

    @Override
    public String toString() {
        return "Administration{"
                + "administration='" + administration + '\''
                + '}';
    }
}