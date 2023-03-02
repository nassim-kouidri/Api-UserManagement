package com.nassim.usermanagement.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Contact")
public class ContactModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    public String name;
    public String email;

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String message;
    public String subject;

    public int phoneNumber;

    public ContactModel(Long id, String name, String email, String message, String subject, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.message = message;
        this.subject = subject;
        this.phoneNumber = phoneNumber;
    }



    public ContactModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
