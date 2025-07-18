package com.example.demo.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Column(name = "user_name", length = 50)
    protected String username;

    @Column(name = "password", length = 50)
    protected String password;

    @Embedded
    protected PersonalInfo personalInfo;

    public User(String username, String password, PersonalInfo personalInfo) {
        this.username = username;
        this.password = password;
        this.personalInfo = personalInfo;
    }

    public User() {}

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }
}
