package com.example.demo.contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("/import.sql")
public class AddressTest {
    @Test
    public void testConstructor(){
        Address address = new Address("Street test", "City test", "State test", "17722",
                                        "123", "USA");
        Assertions.assertEquals("Street test", address.getStreet());
    }
}
