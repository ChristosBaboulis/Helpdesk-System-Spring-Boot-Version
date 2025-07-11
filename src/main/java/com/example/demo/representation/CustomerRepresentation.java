package com.example.demo.representation;

import com.example.demo.contacts.Address;
import org.springframework.aot.hint.annotation.RegisterReflection;

import java.time.LocalDate;

@RegisterReflection
public class CustomerRepresentation {
    public int Id;
    public String customerCode;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public LocalDate birthdate;
    public Address address;

    // No-args constructor for JSON Deserialization
    public CustomerRepresentation() {}
}
