package com.example.demo.persistence;

import com.example.demo.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TechnicianRepository extends JpaRepository<Technician, Integer> {

    List<Technician> findByPersonalInfoLastName(String lastName);

    List<Technician> findByTechCode(String techCode);

    List<Technician> findByPersonalInfoPhone(String phone);

    List<Technician> findByPersonalInfoEmail(String email);

    @Query("SELECT t FROM Technician t WHERE t.username = ?1")
    List<Technician> findByUsername(String username);

//    @Query("SELECT t FROM Technician t JOIN t.specialties s WHERE s.id = ?1")
//    List<Technician> findBySpecialty(Integer specialtyId);
//
//    @Query("SELECT t FROM Technician t JOIN t.specialties s WHERE s.specialtyType = ?1")
//    List<Technician> findBySpecialtyType(String specialtyType);
}