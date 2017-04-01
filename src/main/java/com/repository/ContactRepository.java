package com.repository;

import com.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mmikilchenko on 01.04.2017.
 */
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Contact saveAndFlush(Contact contact);

    List<Contact> findAll();

    Contact findById(Integer id);

    @Transactional
    void deleteById(Integer id);
}
