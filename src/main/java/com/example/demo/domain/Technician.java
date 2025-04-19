package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TECHNICIAN")
public class Technician extends User {
    @Column(name = "tech_code", length = 10)
    private String techCode;

    public Technician(String username, String password, PersonalInfo personalInfo, String techCode) {
        super(username, password, personalInfo);
        this.techCode = techCode;
    }

    public Technician() {}

    public String getTechCode() {
        return techCode;
    }

    public void setTechCode(String techCode) {
        this.techCode = techCode;
    }
}
