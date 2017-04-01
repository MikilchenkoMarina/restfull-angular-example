package com.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author mmikilchenko on 01.04.2017.
 */
@Entity
@Table(name = "public.contact")
public class Contact {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "username", length = 15)
    private String username;

    @Basic
    @Column(name = "address", length = 15)

    private String address;

    @Basic
    @Column(name = "email", length = 15, unique = true)
    private String email;

    public Contact() {
    }

    public Contact(String firstName, String lastName, String userName) {

        this.username = firstName;
        this.address = lastName;
        this.email = userName;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Contact{" +
                ", email='" + this.email + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
