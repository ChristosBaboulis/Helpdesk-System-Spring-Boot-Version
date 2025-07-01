package com.example.demo.persistence;

import com.example.demo.contacts.Address;
import com.example.demo.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TechnicianRepository extends JpaRepository<Technician, Integer> {

    List<Technician> findByTechCode(String techCode);

    List<Technician> findByUsername(String username);

    @Query("SELECT t " +
            "FROM Technician t" +
            " WHERE t.personalInfo.firstName = ?1")
    List<Technician> findByFirstName(String firstName);

    List<Technician> findByPersonalInfoLastName(String lastName);

    List<Technician> findByPersonalInfoEmail(String email);

    List<Technician> findByPersonalInfoPhone(String phone);

    List<Technician> findByPersonalInfoBirthdate(LocalDate birthdate);

    @Query("SELECT t " +
            "FROM Technician t " +
            "WHERE t.personalInfo.address.street = ?1 " +
            "OR t.personalInfo.address.city = ?2 " +
            "OR t.personalInfo.address.state = ?3 " +
            "OR t.personalInfo.address.zipCode = ?4 " +
            "OR t.personalInfo.address.streetNumber = ?5 " +
            "OR t.personalInfo.address.country = ?6")
    List<Technician> findByPersonalInfoAddress(String street, String city, String state,
                                               String zipCode, String streetNumber, String country);

//    @Query("SELECT t FROM Technician t JOIN t.specialties s WHERE s.id = ?1")
//    List<Technician> findBySpecialty(Integer specialtyId);
//
//    @Query("SELECT t FROM Technician t JOIN t.specialties s WHERE s.specialtyType = ?1")
//    List<Technician> findBySpecialtyType(String specialtyType);
}