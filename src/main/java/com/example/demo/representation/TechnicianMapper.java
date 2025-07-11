package com.example.demo.representation;

import com.example.demo.contacts.Address;
import com.example.demo.domain.PersonalInfo;
import com.example.demo.domain.Technician;
import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
/*
,
        uses = {SpecialtyMapper.class}
 */
public abstract class TechnicianMapper {
    public abstract TechnicianRepresentation toRepresentation(Technician technician);

    // @Mapping(target = "specialties", ignore = true)
    public abstract Technician toModel(TechnicianRepresentation representation);

    public abstract List<TechnicianRepresentation> toRepresentationList(List<Technician> technicians);

    @AfterMapping
    protected void extractPersonalInfo(@MappingTarget TechnicianRepresentation representation, Technician technician) {
        if (technician.getPersonalInfo() != null) {
            representation.firstName = technician.getPersonalInfo().getFirstName();
            representation.lastName = technician.getPersonalInfo().getLastName();
            representation.email = technician.getPersonalInfo().getEmail();
            representation.phone = technician.getPersonalInfo().getPhone();
            representation.birthdate = technician.getPersonalInfo().getBirthdate();
            representation.street = technician.getPersonalInfo().getAddress().getStreet();
            representation.streetNumber = technician.getPersonalInfo().getAddress().getStreetNumber();
            representation.zipCode = technician.getPersonalInfo().getAddress().getZipCode();
            representation.country = technician.getPersonalInfo().getAddress().getCountry();
            representation.state = technician.getPersonalInfo().getAddress().getState();
            representation.city = technician.getPersonalInfo().getAddress().getCity();
        } else {
            System.out.println("personalInfo is NULL when mapping Technician → TechnicianRepresentation!");
        }
    }

    @AfterMapping
    protected void afterToModel(TechnicianRepresentation representation, @MappingTarget Technician technician) {
        /*
        if (!representation.specialties.isEmpty()) {
            for(SpecialtyRepresentation specialtyRepresentation : representation.specialties) {
                Specialty specialty = specialtyRepository.findById(specialtyRepresentation.id);
                if (specialty == null) {
                    throw new RuntimeException();
                }
                technician.setSpecialty(specialty);
            }
        }
         */

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setFirstName(representation.firstName);
        personalInfo.setLastName(representation.lastName);
        personalInfo.setEmail(representation.email);
        personalInfo.setPhone(representation.phone);
        personalInfo.setBirthdate(representation.birthdate);
        Address address = new Address();
        address.setCity(representation.city);
        address.setState(representation.state);
        address.setZipCode(representation.zipCode);
        address.setCountry(representation.country);
        address.setStreet(representation.street);
        address.setStreetNumber(representation.streetNumber);
        personalInfo.setAddress(address);

        technician.setPersonalInfo(personalInfo);
    }
}
