package com.latam.test.cumpleanos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.latam.test.cumpleanos.pojo.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{
	
}
