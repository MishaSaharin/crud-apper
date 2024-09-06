package com.saccharine.crudapper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rules", schema = "sites")
public class Rule extends BaseEntity {
    @Column(name = "rule", length = 36)
    private String rule;
    @ManyToMany(mappedBy = "rules")
    private Set<Post> posts;

    public Rule() {
    }

    public Rule(String rule) {
        this.rule = rule;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String name) {
        this.rule = name;
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
        Rule rule = (Rule) o;
        return Objects.equals(rule, rule.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rule);
    }

    @Override
    public String toString() {
        return "Rule{"
                + "name='" + rule + '\''
                + '}';
    }
}