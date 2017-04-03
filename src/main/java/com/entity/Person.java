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
    @Column(name = "location", length = 100)

    private String location;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.name = firstName;
        this.location = lastName;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Person{" +
                ", location='" + this.location + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
