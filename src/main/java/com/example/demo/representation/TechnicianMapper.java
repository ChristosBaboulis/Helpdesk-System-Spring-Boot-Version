package com.example.demo.representation;

import com.example.demo.domain.PersonalInfo;
import com.example.demo.domain.Technician;
import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
/*
,
        uses = {SpecialtyMapper.class}
 */
public abstract class TechnicianMapper {
    public abstract TechnicianRepresentation toRepresentation(Technician technician);

    //@Mapping(target = "specialties", ignore = true)
    public abstract Technician toModel(TechnicianRepresentation representation);

    @AfterMapping
    protected void extractPersonalInfo(@MappingTarget TechnicianRepresentation representation, Technician technician) {
        if (technician.getPersonalInfo() != null) {
            representation.firstName = technician.getPersonalInfo().getFirstName();
            representation.lastName = technician.getPersonalInfo().getLastName();
            representation.email = technician.getPersonalInfo().getEmail();
            representation.phone = technician.getPersonalInfo().getPhone();
            representation.birthdate = technician.getPersonalInfo().getBirthdate();
            representation.address = technician.getPersonalInfo().getAddress();
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
        personalInfo.setAddress(representation.address);

        technician.setPersonalInfo(personalInfo);
    }
}
