package com.saccharine.crudapper.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "posts", schema = "sites")
public class Post extends BaseEntity {
    @Column(name = "name", nullable = false, length = 36)
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "car_number", nullable = false, length = 36)
    private String carNumber;
    @Column(name = "address", nullable = false, length = 36)
    private String address;
    @Column(name = "photo", columnDefinition = "bytea")
    private byte[] photo;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)//
    @JoinTable(name = "post_rules", schema = "sites",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    private Set<Rule> rules = new HashSet<>();

    public Post() {
    }

    public Post(String address, String carNumber, LocalDateTime createdAt, String description, String name, byte[] photo, Set<Rule> rules, Status status, Type type) {
        this.address = address;
        this.carNumber = carNumber;
        this.createdAt = createdAt;
        this.description = description;
        this.name = name;
        this.photo = photo;
        this.rules = rules;
        this.status = status;
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
        Post post = (Post) o;
        return Objects.equals(name, post.name)
                && Objects.equals(description, post.description)
                && Objects.equals(carNumber, post.carNumber)
                && Objects.equals(address, post.address)
                && Objects.deepEquals(photo, post.photo)
                && Objects.equals(createdAt, post.createdAt)
                && Objects.equals(type, post.type)
                && Objects.equals(status, post.status)
                && Objects.equals(rules, post.rules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, carNumber, address, Arrays.hashCode(photo), createdAt, type, status, rules);
    }

    @Override
    public String toString() {
        return "Post{"
                + "address='" + address + '\''
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", carNumber='" + carNumber + '\''
                + ", photo=" + Arrays.toString(photo)
                + ", createdAt=" + createdAt
                + ", type=" + type
                + ", status=" + status
                + ", rules=" + rules
                + '}';
    }
}