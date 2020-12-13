package com.jrescalona.rainalertserver.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    UUID userId = UUID.randomUUID();
    String fName = "John";
    String lName = "Escalona";
    String role = "ADMIN";
    String email = "johnrichmondescalona@gmail.com";
    User user;

    @BeforeEach
    void setUp() {
        this.user  = new User(userId, fName, lName, role, email);
    }

    @Test
    void getId() {
        assertEquals(userId, user.getId());
    }

    @Test
    void setId() {
        UUID newId = UUID.randomUUID();
        user.setId(newId);
        assertEquals(newId, user.getId());
    }

    @Test
    void getFName() {
        assertEquals(fName, user.getFName());
    }

    @Test
    void getLName() {
        assertEquals(lName, user.getLName());
    }

    @Test
    void getRole() {
        assertEquals(role, user.getRole());
    }

    @Test
    void getEmail() {
        assertEquals(email, user.getEmail());
    }
}