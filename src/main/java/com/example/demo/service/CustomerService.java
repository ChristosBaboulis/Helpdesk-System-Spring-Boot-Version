package com.example.demo.service;

import com.example.demo.contacts.Address;
import com.example.demo.domain.Customer;
import com.example.demo.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void saveCustomer(Customer  customer) {
        customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> findByCustomerCode(String customerCode) {
        return customerRepository.findByCustomerCode(customerCode);
    }

    public List<Customer> findByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByPersonalInfoLastName(lastName);
    }

    public List<Customer> findByEmail(String email) {
        return customerRepository.findByPersonalInfoEmail(email);
    }

    public List<Customer> findByPhone(String phone) {
        return customerRepository.findByPersonalInfoPhone(phone);
    }

    public List<Customer> findByBirthdate(LocalDate birthdate) {
        return customerRepository.findByPersonalInfoBirthdate(birthdate);
    }

    public List<Customer> findByAddress(Address address) {
        return customerRepository.findByPersonalInfoAddress(address.getStreet(), address.getCity(),
                address.getState(), address.getZipCode(), address.getStreetNumber(), address.getCountry());
    }
}
