package com.jrescalona.rainalertserver.model;

import java.util.UUID;

public class User {
    UUID id;
    private final String fName;
    private final String lName;
    private final String role;
    private final String email;

    public User(UUID id, String fName, String lName, String role, String email) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.role = role;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }
}
