package com.example.demo.service;

import com.example.demo.domain.Technician;
import com.example.demo.persistence.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianService {
    private final TechnicianRepository technicianRepository;

    @Autowired
    public TechnicianService(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    public List<Technician> getByLastName(String name) {
        return technicianRepository.findByPersonalInfoLastName(name);
    }
}
