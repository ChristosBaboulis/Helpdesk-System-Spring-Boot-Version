package com.example.demo.resource;

import com.example.demo.contacts.Address;
import com.example.demo.domain.Technician;
import com.example.demo.representation.AddressMapper;
import com.example.demo.representation.AddressRepresentation;
import com.example.demo.representation.TechnicianMapper;
import com.example.demo.representation.TechnicianRepresentation;
import com.example.demo.service.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/technicians")
@RestController
public class TechnicianResource {
    @Autowired
    private TechnicianService technicianService;

    @Autowired
    private TechnicianMapper technicianMapper;
    @Autowired
    private AddressMapper addressMapper;

    // GET /technicians - Retrieve all Technicians as a list
    @GetMapping
    public ResponseEntity<List<TechnicianRepresentation>> getAllTechnicians() {
        List<Technician> technicians =  technicianService.findAll();
        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }

    // GET /technicians/{technicianId} - Retrieve specific Technician using Id given as path parameter
    @GetMapping("/{technicianId}")
    public ResponseEntity<TechnicianRepresentation> getTechnicianById(@PathVariable Integer technicianId) {
        Technician technician = technicianService.findById(technicianId);
        if (technician == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(technicianMapper.toRepresentation(technician));
    }

    // GET /tech_code/{technicianCode} - Technicians using technicianCode given as path parameter
    @GetMapping("/tech_code/{technicianCode}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByTechCode(@PathVariable String technicianCode) {
        List<Technician> techniciansList = technicianService.findByTechCode(technicianCode);
        if (techniciansList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(technicianMapper.toRepresentationList(techniciansList));
    }

    // GET /username/{username} - Retrieve Technicians by username given as path parameter
    @GetMapping("/username/{username}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByUsername(@PathVariable String username) {
        List<Technician> technicians = technicianService.findByUsername(username);

        if(technicians.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }

    // GET /first_name/{firstName} - Retrieve Technicians by firstName given as path parameter
    @GetMapping("/first_name/{firstName}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByFirstName(@PathVariable String firstName) {
        List<Technician> technicians = technicianService.findByFirstName(firstName);

        if(technicians.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }

    // GET /last_name/{lastName} - Retrieve Technicians by lastName given as path parameter
    @GetMapping("/last_name/{lastName}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByLastName(@PathVariable String lastName) {
        List<Technician> technicians = technicianService.findByLastName(lastName);

        if (technicians.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }

    // GET /emailAddress/{emailAddress} - Retrieve Technicians by emailAddress given as path parameter
    @GetMapping("/emailAddress/{emailAddress}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByEmail(@PathVariable String emailAddress) {
        List<Technician> technicians = technicianService.findByEmail(emailAddress);

        if (technicians.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }

    // GET /telephoneNumber/{telephoneNumber} - Retrieve Technicians by telephoneNumber given as path parameter
    @GetMapping("/telephoneNumber/{telephoneNumber}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByPhone(@PathVariable String telephoneNumber) {
        List<Technician> technicians = technicianService.findByPhone(telephoneNumber);

        if (technicians.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }

    // GET /birthdate/{birthdate} - Retrieve Technicians by birthdate given as path parameter
    @GetMapping("/birthdate/{birthdate}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByBirthdate(@PathVariable String birthdate) {
        List<Technician> technicians = technicianService.findByBirthdate(LocalDate.parse(birthdate));

        if (technicians.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }

    // GET /address - Retrieve Technicians by address given as request body
    @GetMapping("/address")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByAddress(@RequestBody AddressRepresentation addressRepresentation) {
        Address address = addressMapper.toModel(addressRepresentation);
        if (address.getCountry() == null) {
            return ResponseEntity.badRequest().build();  // country must always have a value
        }
        if (address.getState() != null && address.getCountry() == null) {
            return ResponseEntity.badRequest().build();  // state w/o country -> ERROR
        }
        if (address.getCity() != null && (address.getState() == null || address.getCountry() == null)) {
            return ResponseEntity.badRequest().build();  // city w/o state, country -> ERROR
        }
        if (address.getStreet() != null && (address.getCity() == null || address.getState() == null || address.getCountry() == null)) {
            return ResponseEntity.badRequest().build();  // street w/o city, state, country -> ERROR
        }
        if ((address.getStreetNumber() != null || address.getZipCode() != null) &&
                (address.getStreet() == null || address.getCity() == null || address.getState() == null || address.getCountry() == null)) {
            return ResponseEntity.badRequest().build();  // streetNumber/zipCode w/o everything else -> ERROR
        }

        List<Technician> technicians = technicianService.findByAddress(address);
        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }
}
