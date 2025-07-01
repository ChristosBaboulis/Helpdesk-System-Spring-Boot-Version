package com.example.demo.service;

import com.example.demo.contacts.Address;
import com.example.demo.domain.Customer;
import com.example.demo.domain.CustomerSupport;
import com.example.demo.domain.Technician;
import com.example.demo.persistence.CustomerSupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerSupportService {
    private final CustomerSupportRepository customerSupportRepository;

    @Autowired
    public CustomerSupportService(CustomerSupportRepository customerSupportRepository) {
        this.customerSupportRepository = customerSupportRepository;
    }

    public void save(CustomerSupport customerSupport) {
        customerSupportRepository.save(customerSupport);
    }

    public List<CustomerSupport> findAll() {
        return customerSupportRepository.findAll();
    }

    public CustomerSupport findById(Integer id) {
        return  customerSupportRepository.findById(id).orElse(null);
    }

    public List<CustomerSupport> findByEmplCode(String emplCode) {
        return customerSupportRepository.findByEmpCode(emplCode);
    }

    public List<CustomerSupport> findByUsername(String username) {
        return customerSupportRepository.findByUsername(username);
    }

    public List<CustomerSupport> findByFirstName(String firstName) {
        return customerSupportRepository.findByFirstName(firstName);
    }

    public List<CustomerSupport> findByLastName(String lastName) {
        return customerSupportRepository.findByPersonalInfoLastName(lastName);
    }

    public List<CustomerSupport> findByEmail(String email) {
        return customerSupportRepository.findByPersonalInfoEmail(email);
    }

    public List<CustomerSupport> findByPhone(String phone) {
        return customerSupportRepository.findByPersonalInfoPhone(phone);
    }

    public List<CustomerSupport> findByBirthdate(LocalDate birthdate) {
        return customerSupportRepository.findByPersonalInfoBirthdate(birthdate);
    }

    public List<CustomerSupport> findByAddress(Address address) {
        return customerSupportRepository.findByPersonalInfoAddress(address.getStreet(), address.getCity(),
                address.getState(), address.getZipCode(), address.getStreetNumber(), address.getCountry());
    }
}
