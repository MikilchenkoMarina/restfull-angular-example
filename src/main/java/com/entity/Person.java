package com.entity;

import javax.persistence.*;

/**
 * @author mmikilchenko on 01.04.2017.
 */
@Entity
@Table(name = "public.person")
public class Person {

    @Id
    @Column(name = "pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @Basic
    @Column(name = "name", length = 100)
    private String name;

    @Basic
    @Column(name = "phone", length = 100)
    private String phone;

    @Basic
    @Column(name = "address", length = 100)
    private String address;

    public Person() {
    }

    public Person(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
