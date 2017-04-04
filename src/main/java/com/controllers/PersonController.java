package com.controllers;

import com.entity.Person;
import com.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * @author mmikilchenko on 01.04.2017.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;


    //-------------------Retrieve All Contacts--------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Person>> listAllUsers() {
        List<Person> persons = personService.getAllPersons();
        if (persons.isEmpty()) {
            return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
    }

    //-------------------Retrieve Single Person--------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        Person person = personService.findPersonByPid(id);
        if (person == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }


    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Person person, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + person.getName());

        if (personService.isPersonExisted(person)) {
            System.out.println("A User with name " + person.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        personService.addPerson(person);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(person.getPid()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT )
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        personService.updatePersonByPid(person);
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE )
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Integer id) {
        personService.deletePersonByPid(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}
