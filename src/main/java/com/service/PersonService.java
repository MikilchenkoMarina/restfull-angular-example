package com.service;

import com.entity.Person;
import com.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mmikilchenko on 01.04.2017.
 */
@Transactional
@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public void addPerson(Person person) {
        personRepository.saveAndFlush(person);
    }

    public Person findPersonByPid(Integer pid) {
        return personRepository.findByPid(pid);
    }

    public void deletePersonByPid(int pid) {
        personRepository.deleteByPid(pid);
    }

    public void updatePersonByPid(Person person) {

        Person p = findPersonByPid(person.getPid());
        p.setName(person.getName());
        p.setLocation(person.getLocation());
        personRepository.saveAndFlush(p);
    }


    public void printContactList(List<Person> personList) {
        for (Person person : personList) {
            System.out.println(person.toString());
        }
    }
}
