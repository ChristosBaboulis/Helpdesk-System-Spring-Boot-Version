package com.example.demo.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CUSTOMERSUPPORT")
public class CustomerSupport extends User{
    @Column(name = "emp_code", length = 10)
    private String empCode;

    public CustomerSupport(String username, String password, PersonalInfo personalInfo, String empCode) {
        super(username, password, personalInfo);
        this.empCode = empCode;
    }

    public CustomerSupport() {}

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
}
