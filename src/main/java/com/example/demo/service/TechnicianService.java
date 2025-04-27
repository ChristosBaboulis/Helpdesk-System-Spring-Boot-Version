package com.example.demo.service;

import com.example.demo.contacts.Address;
import com.example.demo.domain.Technician;
import com.example.demo.persistence.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TechnicianService {
    private final TechnicianRepository technicianRepository;

    @Autowired
    public TechnicianService(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    public void saveTechnician(Technician technician) {
        technicianRepository.save(technician);
    }

    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }

    public Technician findById(Integer id){
        return technicianRepository.findById(id).orElse(null);
    }

    public List<Technician> findByTechCode(String techCode) {
        return technicianRepository.findByTechCode(techCode);
    }

    public List<Technician> findByUsername(String username) {
        return technicianRepository.findByUsername(username);
    }

    public List<Technician> findByFirstName(String firstName) {
        return technicianRepository.findByFirstName(firstName);
    }

    public List<Technician> findByLastName(String name) { return technicianRepository.findByPersonalInfoLastName(name); }

    public List<Technician> findByEmail(String email) {
        return technicianRepository.findByPersonalInfoEmail(email);
    }

    public List<Technician> findByPhone(String phone) { return technicianRepository.findByPersonalInfoPhone(phone); }

    public List<Technician> findByBirthdate(LocalDate birthdate) { return technicianRepository.findByPersonalInfoBirthdate(birthdate); }

    public List<Technician> findByAddress(Address address) {
        return technicianRepository.findByPersonalInfoAddress(address.getStreet(), address.getCity(),
                address.getState(), address.getZipCode(), address.getStreetNumber(), address.getCountry());
    }
}
