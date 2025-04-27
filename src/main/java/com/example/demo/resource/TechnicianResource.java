package com.example.demo.resource;

import com.example.demo.contacts.Address;
import com.example.demo.domain.Technician;
import com.example.demo.representation.AddressMapper;
import com.example.demo.representation.AddressRepresentation;
import com.example.demo.representation.TechnicianMapper;
import com.example.demo.representation.TechnicianRepresentation;
import com.example.demo.service.TechnicianService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    //GET TECHNICIANS BY USERNAME
    @GetMapping("/username/{username}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByUsername(@PathVariable String username) {
        List<Technician> technicians = technicianService.findByUsername(username);

        if(technicians.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }

    //GET TECHNICIANS BY FIRSTNAME
    @GetMapping("/first_name/{firstName}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByFirstName(@PathVariable String firstName) {
        List<Technician> technicians = technicianService.findByFirstName(firstName);

        if(technicians.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }

    //GET TECHNICIANS BY LASTNAME
    @GetMapping("/last_name/{lastName}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByLastName(@PathVariable String lastName) {
        List<Technician> technicians = technicianService.findByLastName(lastName);

        if (technicians.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }

    //GET TECHNICIANS BY EMAIL
    @GetMapping("/email/{email}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByEmail(@PathVariable String email) {
        List<Technician> technicians = technicianService.findByEmail(email);

        if (technicians.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }

    //GET TECHNICIANS BY PHONE
    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByPhone(@PathVariable String phone) {
        List<Technician> technicians = technicianService.findByPhone(phone);

        if (technicians.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }

    //GET TECHNICIANS BY BIRTHDATE
    @GetMapping("/birthdate/{birthdate}")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByBirthdate(@PathVariable String birthdate) {
        List<Technician> technicians = technicianService.findByBirthdate(LocalDate.parse(birthdate));

        if (technicians.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }

    //GET TECHNICIANS BY ADDRESS
    @GetMapping("/address")
    public ResponseEntity<List<TechnicianRepresentation>> getTechnicianByAddress(@RequestBody AddressRepresentation addressRepresentation) {
        Address address = addressMapper.toModel(addressRepresentation);
        if (address.getCountry() == null) {
            return ResponseEntity.badRequest().build();  // Country must always have a value
        }
        if (address.getState() != null && address.getCountry() == null) {
            return ResponseEntity.badRequest().build();  // State w/o Country -> ERROR
        }
        if (address.getCity() != null && (address.getState() == null || address.getCountry() == null)) {
            return ResponseEntity.badRequest().build();  // City w/o State, Country -> ERROR
        }
        if (address.getStreet() != null && (address.getCity() == null || address.getState() == null || address.getCountry() == null)) {
            return ResponseEntity.badRequest().build();  // Street w/o City, State, Country -> ERROR
        }
        if ((address.getStreetNumber() != null || address.getZipCode() != null) &&
                (address.getStreet() == null || address.getCity() == null || address.getState() == null || address.getCountry() == null)) {
            return ResponseEntity.badRequest().build();  // StreetNumber/ZipCode w/o everything else -> ERROR
        }

        List<Technician> technicians = technicianService.findByAddress(address);
        return ResponseEntity.ok(technicianMapper.toRepresentationList(technicians));
    }
}
