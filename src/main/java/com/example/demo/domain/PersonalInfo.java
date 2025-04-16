package com.example.demo.domain;

import com.example.demo.contacts.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.time.LocalDate;

@Embeddable
public class PersonalInfo {
    @Column(name = "firstName", length = 50)
    private String firstName;

    @Column(name = "lastName", length = 50)
    private String lastName;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "birthdate", length = 10)
    private LocalDate birthdate;

    @Embedded
    private Address address;

    public PersonalInfo(String firstName, String lastName, String email, String phone, LocalDate birthdate, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.address = address;
    }

    public PersonalInfo() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
