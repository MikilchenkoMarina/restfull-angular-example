package com.service;

import com.entity.Contact;
import com.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mmikilchenko on 01.04.2017.
 */
@Transactional
@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }

    public void addContact(Contact contact) {
        contactRepository.saveAndFlush(contact);
    }

    public Contact findContactById(Integer id) {
        return contactRepository.findById(id);
    }

    public void deleteContactById(int id) {
        contactRepository.deleteById(id);
    }


    public void printContactList(List<Contact> contactList) {
        for (Contact contact : contactList) {
            System.out.println(contact.toString());
        }
    }
}
