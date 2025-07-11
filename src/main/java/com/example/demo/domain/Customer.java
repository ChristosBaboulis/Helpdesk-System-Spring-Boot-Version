package com.example.demo.domain;

import com.example.demo.contacts.Address;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "customer_code", length = 20, nullable = false)
    private String customerCode;

    @Embedded
    private PersonalInfo personalInfo;

    public Customer() {}

    // Constructor with Address as Object arg
    public Customer(String customerCode, String firstName, String lastName,
                    String telephoneNumber, String emailAddress,
                    LocalDate birthdate, Address address) {
        this.customerCode = customerCode;
        this.personalInfo = new PersonalInfo(firstName, lastName, emailAddress, telephoneNumber, birthdate, address);
    }

    public int getId() {
        return id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }
}
