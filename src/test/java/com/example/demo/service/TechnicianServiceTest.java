package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("/import.sql")
public class TechnicianServiceTest {
    @Autowired
    private TechnicianService technicianService;

    @Test
    public void testFindByLastName() {
        Assertions.assertNotNull(technicianService.findByLastName("Christos"));
    }

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
    public void testFindByUsername()  {
        Assertions.assertNotNull(technicianService.findByUsername("tech1"));
    }
}
