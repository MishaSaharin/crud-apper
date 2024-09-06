package com.saccharine.crudapper.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users", schema = "sites")
public class User extends BaseEntity {
    @Column(name = "username", nullable = false, length = 36)
    private String username;
    @Column(name = "password", nullable = false, length = 36)
    private String password;
    @Column(name = "enabled", nullable = false, length = 36)
    private boolean enabled;
    @ManyToOne
    @JoinColumn(name = "administration_id")
    private Administration administration;

    public User() {
    }

    public User(Administration administration, boolean enabled, String password, String username) {
        this.administration = administration;
        this.enabled = enabled;
        this.password = password;
        this.username = username;
    }

    public Administration getAdministration() {
        return administration;
    }

    public void setAdministration(Administration administration) {
        this.administration = administration;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        User user = (User) o;
        return enabled == user.enabled
                && Objects.equals(username, user.username)
                && Objects.equals(password, user.password)
                && Objects.equals(administration, user.administration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, enabled, administration);
    }
}