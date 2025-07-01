package com.example.demo.persistence;

import com.example.demo.domain.CustomerSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CustomerSupportRepository extends JpaRepository<CustomerSupport, Integer> {
    List<CustomerSupport> findByEmpCode(String empCode);

    List<CustomerSupport> findByUsername(String username);

    @Query("SELECT cs " +
            "FROM CustomerSupport cs " +
            "WHERE cs.personalInfo.firstName = ?1")
    List<CustomerSupport> findByFirstName(String firstName);

    List<CustomerSupport> findByPersonalInfoLastName(String lastName);

    List<CustomerSupport> findByPersonalInfoEmail(String email);

    List<CustomerSupport> findByPersonalInfoPhone(String phone);

    List<CustomerSupport> findByPersonalInfoBirthdate(LocalDate birthdate);

    @Query("SELECT cs " +
            "FROM CustomerSupport cs " +
            "WHERE cs.personalInfo.address.street = ?1 " +
            "OR cs.personalInfo.address.city = ?2 " +
            "OR cs.personalInfo.address.state = ?3 " +
            "OR cs.personalInfo.address.zipCode = ?4 " +
            "OR cs.personalInfo.address.streetNumber = ?5 " +
            "OR cs.personalInfo.address.country = ?6")
    List<CustomerSupport> findByPersonalInfoAddress(String street, String city, String state,
                                                    String zipCode, String streetNumber, String country);
}
