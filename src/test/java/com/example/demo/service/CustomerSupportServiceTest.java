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
public class CustomerSupportServiceTest {
    @Autowired
    CustomerSupportService customerSupportService;

    @Test
    public void testFindAll() {
        Assertions.assertNotNull(customerSupportService.findAll());
    }

    @Test
    public void testFindById() {
        Assertions.assertNotNull(customerSupportService.findById(6001));
    }

    @Test
    public void testFindByTechCode() {
        Assertions.assertNotNull(customerSupportService.findByEmplCode("EMPL001"));
    }

    @Test
    public void testFindByUsername() {
        Assertions.assertNotNull(customerSupportService.findByUsername("empl1"));
    }

    @Test
    public void testFindByFirstName() {
        Assertions.assertNotNull(customerSupportService.findByFirstName("Christos"));
    }

    @Test
    public void testFindByLastName() {
        Assertions.assertNotNull(customerSupportService.findByLastName("Emplower"));
    }

    @Test
    public void testFindByEmailAddress() {
        Assertions.assertNotNull(customerSupportService.findByEmailAddress("test.2@example.com"));
    }

    @Test
    public void testFindByTelephoneNumber() {
        Assertions.assertNotNull(customerSupportService.findByTelephoneNumber("1233211232"));
    }

    @Test
    public void testFindByBirthdate() {
        LocalDate birthdate = LocalDate.parse("1995-12-03");
        Assertions.assertNotNull(customerSupportService.findByBirthdate(birthdate));
    }

    @Test
    public void testFindByAddress() {
        Address address = new Address("Alk Street", "San Francisco", "LA", "94541", "101", "USA");
        Assertions.assertNotNull(customerSupportService.findByAddress(address));
    }
}
