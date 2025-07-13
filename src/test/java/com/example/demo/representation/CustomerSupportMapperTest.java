package com.example.demo.representation;

import com.example.demo.domain.CustomerSupport;
import com.example.demo.service.CustomerSupportService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

@SpringBootTest
@Sql("/import.sql")
public class CustomerSupportMapperTest {
    @Autowired
    private CustomerSupportMapper customerSupportMapper;

    @Autowired
    private CustomerSupportService customerSupportService;

    @Test
    @Transactional
    public void testMapperWithPersonalInfo(){
        CustomerSupportRepresentation customerSupportRepresentation = new CustomerSupportRepresentation();
        customerSupportRepresentation.empCode = "123";
        customerSupportRepresentation.firstName = "John";
        customerSupportRepresentation.lastName = "Doe";
        customerSupportRepresentation.username = "johnDoe";
        customerSupportRepresentation.password = "password";
        customerSupportRepresentation.phone = "123456789";
        customerSupportRepresentation.email = "johndoe@example.com";
        customerSupportRepresentation.birthdate = LocalDate.of(1990, 1, 1);
        customerSupportRepresentation.city = "San Francisco";
        customerSupportRepresentation.state = "LA";
        customerSupportRepresentation.country = "USA";
        customerSupportRepresentation.zipCode = "94102";
        customerSupportRepresentation.street = "Elm Street";
        customerSupportRepresentation.streetNumber = "10";

        CustomerSupport technician = customerSupportMapper.toModel(customerSupportRepresentation);

        Assertions.assertEquals("123", technician.getEmpCode());
        Assertions.assertEquals("John", technician.getPersonalInfo().getFirstName());
        Assertions.assertEquals("Doe", technician.getPersonalInfo().getLastName());
        Assertions.assertEquals("johndoe@example.com", technician.getPersonalInfo().getEmail());
        Assertions.assertEquals("123456789", technician.getPersonalInfo().getPhone());
        Assertions.assertEquals("johnDoe", technician.getUsername());
        Assertions.assertEquals("password", technician.getPassword());
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), technician.getPersonalInfo().getBirthdate());
        Assertions.assertNotNull(technician.getPersonalInfo().getAddress());

        customerSupportService.save(technician);

        CustomerSupportRepresentation representation2 = customerSupportMapper.toRepresentation(technician);
        Assertions.assertNotNull(representation2);
        Assertions.assertEquals("123", representation2.empCode);
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
        CustomerSupport customerSupport = customerSupportService.findById(6001);
        Assertions.assertNotNull(customerSupport);

        customerSupport.setPersonalInfo(null);
        Assertions.assertNull(customerSupport.getPersonalInfo());
        customerSupportMapper.toRepresentation(customerSupport);
    }

    @Test
    public void testNullMappings(){
        customerSupportMapper.toRepresentation(null);
        customerSupportMapper.toModel(null);
        customerSupportMapper.toRepresentationList(null);
    }
}
