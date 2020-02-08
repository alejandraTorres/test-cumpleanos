package com.latam.test.cumpleanos.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latam.test.cumpleanos.helper.RestHelper;
import com.latam.test.cumpleanos.pojo.Person;
import com.latam.test.cumpleanos.repository.PersonRepository;
import com.latam.test.cumpleanos.services.BirthdayProcess;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CumpleController {
	
	@Autowired
	BirthdayProcess servicio;
	
	@Autowired
	RestHelper restHelper;

	@Autowired
	private PersonRepository repository;
	

	CumpleController(PersonRepository personRepository) {
        this.repository = personRepository;
    }

	@CrossOrigin("*")
	@PostMapping(value = "/setPerson", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String setPerson(@RequestBody String InformacionPersona) throws ClassNotFoundException, IOException, ParseException {
		log.info("Informacion recibida: " + InformacionPersona);
		Person person = new Person();

		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = mapper.readValue(InformacionPersona,Map.class);
		person.setName(map.get("name"));
		person.setLastName(map.get("lastName"));
		person.setBirthDate(map.get("birthDate"));
		repository.save(person);
		
		return "";
	}
	
	@CrossOrigin("*")
	@GetMapping(value = "/getPersons", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String getPersons() throws ClassNotFoundException, IOException, ParseException {
		List<Person> persons = repository.findAll();
		persons = servicio.personInformation(persons);
		
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(persons);
	}
	
	
	
}
