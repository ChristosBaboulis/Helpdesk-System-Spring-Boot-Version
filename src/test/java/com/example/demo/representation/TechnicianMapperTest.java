package com.example.demo.representation;

import com.example.demo.domain.Technician;
import com.example.demo.service.TechnicianService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

@SpringBootTest
@Sql("/import.sql")
public class TechnicianMapperTest {
    @Autowired
    private TechnicianMapper technicianMapper;

    @Autowired
    private TechnicianService technicianService;

    @Test
    @Transactional
    public void testMapperWithPersonalInfo(){
        TechnicianRepresentation technicianRepresentation = new TechnicianRepresentation();
        technicianRepresentation.techCode = "123";
        technicianRepresentation.firstName = "John";
        technicianRepresentation.lastName = "Doe";
        technicianRepresentation.username = "johnDoe";
        technicianRepresentation.password = "password";
        technicianRepresentation.phone = "123456789";
        technicianRepresentation.email = "johndoe@example.com";
        technicianRepresentation.birthdate = LocalDate.of(1990, 1, 1);
        technicianRepresentation.city = "San Francisco";
        technicianRepresentation.state = "LA";
        technicianRepresentation.country = "USA";
        technicianRepresentation.zipCode = "94102";
        technicianRepresentation.street = "Elm Street";
        technicianRepresentation.streetNumber = "10";

        Technician technician = technicianMapper.toModel(technicianRepresentation);

        Assertions.assertEquals("123", technician.getTechCode());
        Assertions.assertEquals("John", technician.getPersonalInfo().getFirstName());
        Assertions.assertEquals("Doe", technician.getPersonalInfo().getLastName());
        Assertions.assertEquals("johndoe@example.com", technician.getPersonalInfo().getEmail());
        Assertions.assertEquals("123456789", technician.getPersonalInfo().getPhone());
        Assertions.assertEquals("johnDoe", technician.getUsername());
        Assertions.assertEquals("password", technician.getPassword());
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), technician.getPersonalInfo().getBirthdate());
        Assertions.assertNotNull(technician.getPersonalInfo().getAddress());

        technicianService.save(technician);

        TechnicianRepresentation representation2 = technicianMapper.toRepresentation(technician);
        Assertions.assertNotNull(representation2);
        Assertions.assertEquals("123", representation2.techCode);
        Assertions.assertEquals("John", representation2.firstName);
        Assertions.assertEquals("Doe", representation2.lastName);
        Assertions.assertEquals("johndoe@example.com", representation2.email);
        Assertions.assertEquals("123456789", representation2.phone);
        Assertions.assertEquals("johnDoe", representation2.username);
        Assertions.assertEquals("password", representation2.password);
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), representation2.birthdate);
        Assertions.assertEquals("San Francisco", representation2.city);
        Assertions.assertEquals("LA", representation2.state);
        Assertions.assertEquals("USA", representation2.country);
        Assertions.assertEquals("94102", representation2.zipCode);
        Assertions.assertEquals("Elm Street", representation2.street);
        Assertions.assertEquals("10", representation2.streetNumber);
        Assertions.assertNotNull(representation2.id);
    }

    @Test
    @Transactional
    public void testMapperWOPersonalInfo(){
        Technician technician = technicianService.findById(4001);
        Assertions.assertNotNull(technician);

        technician.setPersonalInfo(null);
        Assertions.assertNull(technician.getPersonalInfo());
        technicianMapper.toRepresentation(technician);
    }

    @Test
    public void testNullMappings(){
        technicianMapper.toRepresentation(null);
        technicianMapper.toModel(null);
        technicianMapper.toRepresentationList(null);
    }
}
