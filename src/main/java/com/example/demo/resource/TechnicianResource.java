package com.example.demo.resource;

import com.example.demo.domain.Technician;
import com.example.demo.representation.TechnicianMapper;
import com.example.demo.representation.TechnicianRepresentation;
import com.example.demo.service.TechnicianService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/technicians")
@RestController
public class TechnicianResource {
    @Autowired
    private TechnicianService technicianService;

    @Autowired
    private TechnicianMapper technicianMapper;

    //GET ALL TECHNICIANS AS A LIST
    @GetMapping
    public ResponseEntity<List<TechnicianRepresentation>> getAllTechnicians() {
        List<Technician> technicians =  technicianService.findAll();
        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }

    //GET SPECIFIC TECHNICIAN BY ID GIVEN IN PATH
    @GetMapping("/{id}")
    public ResponseEntity<TechnicianRepresentation> getTechnicianById(@PathVariable Integer id) {
        Technician technician = technicianService.findById(id);
        if (technician == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(technicianMapper.toRepresentation(technician));
    }

    //GET TECHNICIANS BY TECHNICIAN CODE
    @GetMapping("/tech_code/{techCode}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByTechCode(@PathVariable String techCode) {
        List<Technician> techniciansList = technicianService.findByTechCode(techCode);
        if (techniciansList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(technicianMapper.toRepresentationList(techniciansList));
    }

    //GET TECHNICIAN BY USERNAME
    @GetMapping("/username/{username}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByUsername(@PathVariable String username) {
        List<Technician> technicians = technicianService.findByUsername(username);

        if(technicians.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }
}
