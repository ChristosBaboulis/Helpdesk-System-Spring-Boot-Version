package com.example.demo.service;

import com.example.demo.contacts.Address;
import com.example.demo.domain.Customer;
import com.example.demo.domain.PersonalInfo;
import com.example.demo.persistence.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@Sql("/import.sql")
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void testFindAll() { Assertions.assertNotNull(customerService.findAll()); }

    @Test
    public void testFindById() { Assertions.assertNotNull(customerService.findById(5001)); }

    @Test
    public void testFindByCustomerCode() { Assertions.assertNotNull(customerService.findByCustomerCode("cust1")); }

    @Test
    public void testFindByFirstName() { Assertions.assertNotNull(customerService.findByFirstName("Christos")); }

    @Test
    public void testFindByLastName() { Assertions.assertNotNull(customerService.findByLastName("Doe")); }

    @Test
    public void testFindByEmail() { Assertions.assertNotNull(customerService.findByEmail("c@b.gr")); }

    @Test
    public void testFindByPhone() { Assertions.assertNotNull(customerService.findByPhone("6911111111")); }

    @Test
    public void testFindByBirthdate() {
        Assertions.assertNotNull(customerService.findByBirthdate(LocalDate.parse("1999-09-15")));
    }

    @Test
    public void testFindByAddress() {
        Address address = new Address("Elm Street", "Minitown",
                "California", "10122", "666", "USA");
        Assertions.assertNotNull(customerService.findByAddress(address));
    }

    @Test
    public void testSaveCustomer() {
        LocalDate date = LocalDate.parse("03/01/1990",
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Customer customer = new Customer();
        customer.setCustomerCode("cust2");
        customer.setPersonalInfo(new PersonalInfo());
        customer.getPersonalInfo().setFirstName("Christos");
        customer.getPersonalInfo().setLastName("Bampoulis");
        customer.getPersonalInfo().setEmail("chrisb2603@gmail.com");
        customer.getPersonalInfo().setPhone("6911111111");
        customer.getPersonalInfo().setBirthdate(date);
        customer.getPersonalInfo().setAddress(new Address("Gripari", "Kallithea",
                "Attiki", "12345", "165", "Greece"));

        customerService.saveCustomer(customer);

        Customer customer1 = customerService.findByCustomerCode("cust2").getFirst();

        Assertions.assertEquals("Christos", customer1.getPersonalInfo().getFirstName());
        Assertions.assertEquals("Bampoulis", customer1.getPersonalInfo().getLastName());
        Assertions.assertEquals("chrisb2603@gmail.com", customer1.getPersonalInfo().getEmail());
        Assertions.assertEquals("6911111111", customer1.getPersonalInfo().getPhone());
        Assertions.assertEquals(date, customer1.getPersonalInfo().getBirthdate());
        Assertions.assertEquals("Gripari", customer1.getPersonalInfo().getAddress().getStreet());
        Assertions.assertEquals("Kallithea", customer1.getPersonalInfo().getAddress().getCity());
        Assertions.assertEquals("Attiki", customer1.getPersonalInfo().getAddress().getState());
        Assertions.assertEquals("Greece", customer1.getPersonalInfo().getAddress().getCountry());
        Assertions.assertEquals("165", customer1.getPersonalInfo().getAddress().getStreetNumber());
        Assertions.assertEquals("12345", customer1.getPersonalInfo().getAddress().getZipCode());
    }
}
