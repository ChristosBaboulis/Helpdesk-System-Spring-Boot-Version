package com.example.demo.domain;

import com.example.demo.contacts.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate birthdate = LocalDate.parse("03/01/1990", formatter);
    Customer customer;
    Customer customer2;
    Customer customer3;
    Address address;

    @BeforeEach
    public void setup(){

        customer = new Customer("123 customer code",
                "Christos3", "Bampoulis3",
                "69999991", "cb3@gg3.gr",
                birthdate, address
        );

        address = new Address("Gripari", "Kallithea", "Attiki", "12345", "165", "Greece");
        customer2 = new Customer("1234 customer code",
                "Christos4", "Bampoulis4",
                "69999992", "cb3@gg4.gr",
                birthdate, address
        );

        customer3 = new Customer();
    }

    @Test
    public void checkGettersAndSetters(){
        //GETTER, SETTER IF CUSTOMER CODE
        assertEquals("123 customer code", customer.getCustomerCode());
        customer.setCustomerCode("new code");

        //GETTER, SETTER OF ASSOCIATED PERSONAL INFO
        assertEquals("Christos3", customer.getPersonalInfo().getFirstName());
        customer.getPersonalInfo().setFirstName("new name");

        assertEquals("Bampoulis3", customer.getPersonalInfo().getLastName());
        customer.getPersonalInfo().setLastName("last name");

        assertEquals("cb3@gg3.gr", customer.getPersonalInfo().getEmail());
        customer.getPersonalInfo().setEmail("test@gmail.com");

        assertEquals(address, customer2.getPersonalInfo().getAddress());
        customer.getPersonalInfo().setAddress(address);

        assertEquals(birthdate, customer.getPersonalInfo().getBirthdate());
        customer.getPersonalInfo().setBirthdate(birthdate);

        assertEquals("69999991", customer.getPersonalInfo().getPhone());
        customer.getPersonalInfo().setPhone("1234567890");

        customer.setPersonalInfo(new PersonalInfo());
    }

}