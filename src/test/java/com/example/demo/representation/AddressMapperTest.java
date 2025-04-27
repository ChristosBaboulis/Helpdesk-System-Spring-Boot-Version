package com.example.demo.representation;

import com.example.demo.contacts.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void testAddressMapper(){
        AddressRepresentation addressRepresentation = new AddressRepresentation();
        addressRepresentation.city = "San Francisco";
        addressRepresentation.country = "USA";
        addressRepresentation.state = "CA";
        addressRepresentation.zipCode = "12345";
        addressRepresentation.street = "Some street";
        addressRepresentation.streetNumber = "12345";

        Address address = addressMapper.toModel(addressRepresentation);

        Assertions.assertEquals("San Francisco", address.getCity());
        Assertions.assertEquals("USA", address.getCountry());
        Assertions.assertEquals("CA", address.getState());
        Assertions.assertEquals("12345", address.getZipCode());
        Assertions.assertEquals("Some street", address.getStreet());
        Assertions.assertEquals("12345", address.getStreetNumber());

        AddressRepresentation addressRepresentation2 = addressMapper.toRepresentation(address);
        Assertions.assertEquals("San Francisco", addressRepresentation2.city);
        Assertions.assertEquals("USA", addressRepresentation2.country);
        Assertions.assertEquals("CA", addressRepresentation2.state);
        Assertions.assertEquals("12345", addressRepresentation2.zipCode);
        Assertions.assertEquals("Some street", addressRepresentation2.street);
        Assertions.assertEquals("12345", addressRepresentation2.streetNumber);
    }

    @Test
    public void testNullMappings(){
        addressMapper.toRepresentation(null);
        addressMapper.toModel(null);
    }
}
