package com.nassim.usermanagement.DTO.Response;

import com.nassim.usermanagement.Model.Gender;

import java.io.Serializable;
import java.util.Date;

public class UserResponse implements Serializable {

    private Long id;
    private String name;
    private Gender gender;
    private Date dateOfBirth;
    private String address;


    public UserResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

