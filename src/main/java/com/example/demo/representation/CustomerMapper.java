package com.example.demo.representation;

import com.example.demo.domain.Customer;
import com.example.demo.domain.PersonalInfo;
import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class CustomerMapper {

    public abstract Customer toModel(CustomerRepresentation customerRepresentation);

    public abstract CustomerRepresentation toRepresentation(Customer customer);

    public abstract List<CustomerRepresentation> toRepresentationList(List<Customer> customers);

    @AfterMapping
    public void fillPersonalInfo(CustomerRepresentation customerRepresentation, @MappingTarget Customer customer) {
        if(customer.getPersonalInfo() == null){
            customer.setPersonalInfo(new PersonalInfo());
        }

        customer.getPersonalInfo().setFirstName(customerRepresentation.firstName);
        customer.getPersonalInfo().setLastName(customerRepresentation.lastName);
        customer.getPersonalInfo().setEmail(customerRepresentation.email);
        customer.getPersonalInfo().setEmail(customerRepresentation.email);
        customer.getPersonalInfo().setPhone(customerRepresentation.phone);
        customer.getPersonalInfo().setBirthdate(customerRepresentation.birthdate);
        customer.getPersonalInfo().setAddress(customerRepresentation.address);
    }

    @AfterMapping
    public void extractPersonalInfo(Customer customer, @MappingTarget CustomerRepresentation customerRepresentation) {
        if(customer.getPersonalInfo() != null){
            customerRepresentation.firstName = customer.getPersonalInfo().getFirstName();
            customerRepresentation.lastName = customer.getPersonalInfo().getLastName();
            customerRepresentation.email = customer.getPersonalInfo().getEmail();
            customerRepresentation.phone = customer.getPersonalInfo().getPhone();
            customerRepresentation.birthdate = customer.getPersonalInfo().getBirthdate();
            customerRepresentation.address = customer.getPersonalInfo().getAddress();
        }else{
            throw new IllegalStateException("PersonalInfo is missing in Customer Entity - Cannot convert to representation!");
        }
    }
}
