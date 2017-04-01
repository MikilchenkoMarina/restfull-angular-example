package com.controllers;

import com.entity.Contact;
import com.service.ContactService;
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
public class ContactController {

    @Autowired
    ContactService contactService;

    //-------------------Retrieve All Contacts--------------------------------------------------------

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ResponseEntity<List<Contact>> listAllUsers() {
        List<Contact> contacts = contactService.findAllContacts();
        if (contacts.isEmpty()) {
            return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }

    //-------------------Retrieve Single Contact--------------------------------------------------------

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        Contact contact = contactService.findContactById(id);
        if (contact == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Contact>(contact, HttpStatus.OK);
    }


    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Contact contact, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + contact.getUsername());

     /*   if (contactService.isUserExist(contact)) {
            System.out.println("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/

        contactService.addContact(contact);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/contact/{id}").buildAndExpand(contact.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


}
