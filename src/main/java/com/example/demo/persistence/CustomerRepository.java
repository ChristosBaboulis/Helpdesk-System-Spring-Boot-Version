package com.example.demo.persistence;

import com.example.demo.domain.Customer;
import com.example.demo.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByCustomerCode(String customerCode);

    @Query("SELECT c FROM Customer c WHERE c.personalInfo.firstName = ?1")
    List<Customer> findByFirstName(String firstName);

    List<Customer> findByPersonalInfoLastName(String lastName);

    List<Customer> findByPersonalInfoEmail(String email);

    List<Customer> findByPersonalInfoPhone(String phone);

    List<Customer> findByPersonalInfoBirthdate(LocalDate birthdate);

    @Query("SELECT c FROM Customer c WHERE c.personalInfo.address.street = ?1 OR c.personalInfo.address.city = ?2 OR c.personalInfo.address.state = ?3 OR c.personalInfo.address.zipCode = ?4 OR c.personalInfo.address.streetNumber = ?5 OR c.personalInfo.address.country = ?6")
    List<Customer> findByPersonalInfoAddress(String street, String city, String state, String zipCode, String streetNumber, String country);
}
