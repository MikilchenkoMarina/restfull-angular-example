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

    public boolean isPersonExisted(Person person) {
        for (Person existedPerson : personRepository.findAll()) {
            if (existedPerson.getName().equals(person.getName())
                    && existedPerson.getPhone().equals(person.getPhone())
                    && existedPerson.getAddress().equals(person.getAddress())) {
                return true;
            }
        }
        return false;
    }

    public void updatePersonByPid(Person person) {

        Person p = findPersonByPid(person.getPid());
        p.setName(person.getName());
        p.setPhone(person.getPhone());
        p.setAddress(person.getAddress());
        personRepository.saveAndFlush(p);
    }

}
