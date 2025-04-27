package com.example.demo.representation;

import com.example.demo.contacts.Address;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class AddressMapper {

    public abstract Address toModel(AddressRepresentation addressRepresentation);

    public abstract AddressRepresentation toRepresentation(Address address);
}
