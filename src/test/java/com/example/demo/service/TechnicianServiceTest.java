package com.example.demo.service;

import com.example.demo.contacts.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

@SpringBootTest
@Sql("/import.sql")
public class TechnicianServiceTest {
    @Autowired
    private TechnicianService technicianService;

    @Test
    public void testFindAll(){
        Assertions.assertNotNull(technicianService.findAll());
    }

    @Test
    public void testFindById(){
        Assertions.assertNotNull(technicianService.findById(4001));
    }

    @Test
    public void testFindByTechCode(){
        Assertions.assertNotNull(technicianService.findByTechCode("TECH001"));
    }

    @Test
    public void testFindByUsername()  { Assertions.assertNotNull(technicianService.findByUsername("tech1")); }

    @Test
    public void testFindByFirstName() { Assertions.assertNotNull(technicianService.findByFirstName("Christos")); }

    @Test
    public void testFindByLastName() { Assertions.assertNotNull(technicianService.findByLastName("Brown")); }

    @Test
    public void testFindByEmailAddress() { Assertions.assertNotNull(technicianService.findByEmailAddress("david.brown@example.com")); }

    @Test
    public void testFindByTelephoneNumber() { Assertions.assertNotNull(technicianService.findByTelephoneNumber("3216549870")); }

    @Test
    public void testFindByBirthdate() {
        LocalDate birthdate = LocalDate.parse("1985-09-15");
        Assertions.assertNotNull(technicianService.findByBirthdate(birthdate));
    }

    @Test
    public void testFindByAddress() {
        Address address = new Address("Elm Street", "San Francisco", "LA", "94102", "10", "USA");
        Assertions.assertNotNull(technicianService.findByAddress(address));
    }
}
