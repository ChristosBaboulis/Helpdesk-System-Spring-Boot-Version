package com.example.demo.representation;

import com.example.demo.contacts.Address;
import com.example.demo.domain.Customer;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class CustomerMapperTest {
    @Autowired
    CustomerMapper customerMapper;

    LocalDate date = LocalDate.parse("03/01/1990",
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    Address address = new Address("Gripari", "Kallithea",
            "Attiki", "12345", "165", "Greece");

    @Test
    public void testCustomerMapper(){
        CustomerRepresentation customerRepresentation = new CustomerRepresentation();
        customerRepresentation.firstName = "Christos";
        customerRepresentation.lastName = "Bampoulis";
        customerRepresentation.email = "chrisb2603@gmail.com";
        customerRepresentation.phone = "6911111111";
        customerRepresentation.birthdate = date;
        customerRepresentation.address = address;

        Customer customer = customerMapper.toModel(customerRepresentation);

        Assertions.assertEquals("Christos", customer.getPersonalInfo().getFirstName());
        Assertions.assertEquals("Bampoulis", customer.getPersonalInfo().getLastName());
        Assertions.assertEquals("chrisb2603@gmail.com", customer.getPersonalInfo().getEmail());
        Assertions.assertEquals("6911111111", customer.getPersonalInfo().getPhone());
        Assertions.assertEquals(date, customer.getPersonalInfo().getBirthdate());
        Assertions.assertEquals("Gripari", customer.getPersonalInfo().getAddress().getStreet());
        Assertions.assertEquals("Kallithea", customer.getPersonalInfo().getAddress().getCity());
        Assertions.assertEquals("Attiki", customer.getPersonalInfo().getAddress().getState());
        Assertions.assertEquals("Greece", customer.getPersonalInfo().getAddress().getCountry());
        Assertions.assertEquals("165", customer.getPersonalInfo().getAddress().getStreetNumber());
        Assertions.assertEquals("12345", customer.getPersonalInfo().getAddress().getZipCode());

        CustomerRepresentation customerRepresentation2 = customerMapper.toRepresentation(customer);
        Assertions.assertEquals("Christos", customerRepresentation2.firstName);
        Assertions.assertEquals("Bampoulis", customerRepresentation2.lastName);
        Assertions.assertEquals("chrisb2603@gmail.com", customerRepresentation2.email);
        Assertions.assertEquals("6911111111", customerRepresentation2.phone);
        Assertions.assertEquals(date, customerRepresentation2.birthdate);
        Assertions.assertEquals(address, customerRepresentation2.address);
    }
}
