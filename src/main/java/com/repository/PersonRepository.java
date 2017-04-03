package com.repository;

import com.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mmikilchenko on 01.04.2017.
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person saveAndFlush(Person person);

    List<Person> findAll();

    Person findByPid(Integer pid);

    @Transactional
    void deleteByPid(Integer pid);
}
