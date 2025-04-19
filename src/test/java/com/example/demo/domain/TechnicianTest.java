package com.example.demo.domain;

import com.example.demo.contacts.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TechnicianTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate birthdate = LocalDate.parse("03/01/1990", formatter);
    Technician technician;
    PersonalInfo personalInfo;
    Address address;

    @BeforeEach
    public void setUp() {
        personalInfo = new PersonalInfo("tec_firstName", "tec_lastName", "1234567899", "ct@bt.gr", birthdate, address);
        technician = new Technician("tec_username", "tec_password", personalInfo,"123_tec_code");
    }

    @Test
    public void testGettersAndSetters() {
        assert technician.getPersonalInfo().getFirstName().equals("tec_firstName");
    }
}
