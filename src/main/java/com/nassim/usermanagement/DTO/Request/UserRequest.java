package com.nassim.usermanagement.DTO.Request;

import com.nassim.usermanagement.Model.Gender;

import java.io.Serializable;
import java.util.Date;

public class UserRequest implements Serializable {
    private String name;


    private Gender gender;

    private Date dateOfBirth;

    private String address;



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
}
