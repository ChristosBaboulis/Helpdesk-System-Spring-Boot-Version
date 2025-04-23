package com.example.demo.domain;

import com.example.demo.contacts.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerSupportTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate birthdate = LocalDate.parse("03/01/1990", formatter);
    CustomerSupport customerSupport;
    PersonalInfo personalInfo;
    Address address;

    @BeforeEach
    public void setUp() {
        personalInfo = new PersonalInfo("cs_firstName", "cs_lastName", "1234567890", "c@b.gr", birthdate, address);
        customerSupport = new CustomerSupport("cs_username", "cs_password", personalInfo,"123_empl_code");
    }

    @Test
    public void testGettersAndSetters() {
        Assertions.assertEquals("cs_firstName", customerSupport.getPersonalInfo().getFirstName());
        Assertions.assertEquals("123_empl_code", customerSupport.getEmpCode());

        customerSupport.setEmpCode("321_empl_code");
        Assertions.assertEquals("321_empl_code", customerSupport.getEmpCode());
    }

    @Test
    public void testEmptyConstructor() {
        CustomerSupport customerSupport = new CustomerSupport();
        Assertions.assertNotNull(customerSupport);
    }
}
