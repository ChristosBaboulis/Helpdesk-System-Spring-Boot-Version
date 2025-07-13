package com.example.demo.representation;

import com.example.demo.contacts.Address;
import com.example.demo.domain.CustomerSupport;
import com.example.demo.domain.PersonalInfo;
import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public abstract class CustomerSupportMapper {
    public abstract CustomerSupportRepresentation toRepresentation(CustomerSupport customerSupport);

    public abstract CustomerSupport toModel(CustomerSupportRepresentation representation);

    public abstract List<CustomerSupportRepresentation> toRepresentationList(List<CustomerSupport> customerSupports);

    @AfterMapping
    protected void extractPersonalInfo(@MappingTarget CustomerSupportRepresentation representation, CustomerSupport customerSupport) {
        if (customerSupport.getPersonalInfo() != null) {
            representation.firstName = customerSupport.getPersonalInfo().getFirstName();
            representation.lastName = customerSupport.getPersonalInfo().getLastName();
            representation.email = customerSupport.getPersonalInfo().getEmail();
            representation.phone = customerSupport.getPersonalInfo().getPhone();
            representation.birthdate = customerSupport.getPersonalInfo().getBirthdate();
            representation.street = customerSupport.getPersonalInfo().getAddress().getStreet();
            representation.streetNumber = customerSupport.getPersonalInfo().getAddress().getStreetNumber();
            representation.zipCode = customerSupport.getPersonalInfo().getAddress().getZipCode();
            representation.country = customerSupport.getPersonalInfo().getAddress().getCountry();
            representation.state = customerSupport.getPersonalInfo().getAddress().getState();
            representation.city = customerSupport.getPersonalInfo().getAddress().getCity();
        } else {
            System.out.println("personalInfo is NULL when mapping CustomerSupport → CustomerSupportRepresentation!");
        }
    }

    @AfterMapping
    protected void afterToModel(CustomerSupportRepresentation representation, @MappingTarget CustomerSupport customerSupport) {
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

        customerSupport.setPersonalInfo(personalInfo);
    }
}
