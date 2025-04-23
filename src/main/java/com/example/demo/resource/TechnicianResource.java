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

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Technician> getTechnicians() {
        return technicianService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianRepresentation> getTechnician(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Technician technician = technicianService.findBy(id);
        if (technician == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(technicianMapper.toRepresentation(technician));
    }

}
