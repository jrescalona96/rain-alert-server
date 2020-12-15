package com.jrescalona.rainalertserver.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    UUID projectId = UUID.randomUUID();
    UUID addressId = UUID.randomUUID();
    UUID locationGridId = UUID.randomUUID();

    String projectName = "Project 1";
    String projectDescription = "Methane Mitigation Design Level II.";

    String gridId  = "LOX";
    int gridX = 432;
    int gridY = 10;
    double longitude = 34.0589;
    double latitude = -117.8194;

    Location locationGrid = new Location(
            locationGridId,
            gridId,
            gridX,
            gridY,
            longitude,
            latitude);

    String addressLine1 = "1234 Five Six Ave.";
    String addressLine2 = "Apt. Seven";
    String city = "Eight Heights";
    String state = "NY";
    String zip = "98765";

    Address projectAddress = new Address(
            addressId,
            addressLine1,
            addressLine2,
            city,
            state,
            zip,
            locationGrid);;

    Project project = new Project(
            projectId,
            projectName,
            projectDescription,
            projectAddress);

    @BeforeEach
    void setUp() {

    }

    @Test
    void getIdShouldReturnUUID() {
        assertEquals(project.getId(), projectId);
    }

    @Test
    void getName() {
        assertEquals(project.getName(), projectName);
    }

    @Test
    void getDescription() {
        assertEquals(project.getDescription(), projectDescription);
    }

    @Test
    void getAddressNotNull() {
        assertNotNull(project.getAddress());
    }

    @Test
    void getAddressReturnsSameAddress() {
        assertEquals(projectAddress, project.getAddress());
    }

    @Test
    void equalsSameParametersShouldReturnTrue() {
        Project otherProject = new Project(
                projectId,
                projectName,
                projectDescription,
                projectAddress);

        assertTrue(project.equals(otherProject));
    }

    @Test
    void equalsDiffIdShouldReturnTrue() {
        Project otherProject = new Project(
                UUID.randomUUID(),
                projectName,
                projectDescription,
                projectAddress);

        assertTrue(project.equals(otherProject));
    }

    @Test
    void equalsWithSameAddressOnlyShouldReturnFalse() {
        Project otherProject = new Project(
                UUID.randomUUID(),
                "Set up",
                "Not the same project",
                projectAddress);
        assertFalse(project.equals(otherProject));
    }
}