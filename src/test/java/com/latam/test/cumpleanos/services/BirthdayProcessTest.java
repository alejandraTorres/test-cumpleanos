package com.latam.test.cumpleanos.services;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.latam.test.cumpleanos.pojo.Person;

class BirthdayProcessTest {

	BirthdayProcess bp = new BirthdayProcess();
	
	@Test
	void birthdayDays() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate now = LocalDate.parse("08-02-2020", formatter);
		assertEquals(bp.birthdayDays(now, LocalDate.parse("10-03-1990", formatter)), 31);
		assertEquals(bp.birthdayDays(now, LocalDate.parse("01-01-1990", formatter)), 328);
		assertEquals(bp.birthdayDays(now, LocalDate.parse("07-02-1990", formatter)), 365);
		assertEquals(bp.birthdayDays(now, now), 0);
	}
	
	@Test
	void personAge() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate now = LocalDate.parse("08-02-2020", formatter);
		assertEquals(bp.personAge(now, LocalDate.parse("09-03-1990", formatter)), 29);
		assertEquals(bp.personAge(now, LocalDate.parse("07-02-1990", formatter)), 30);
	}
	
	@Test
	void personInformation() {
		Person p = new Person();
		p.setName("Alejandra");
		p.setLastName("Torres");
		p.setBirthDate("09-03-1990");
		List<Person> ps = new ArrayList<Person>();
		ps.add(p);
		try {
			ps = bp.personInformation(ps);
			p = ps.get(0);
			assertNotEquals(p.getAge(), null);
			assertEquals(p.getBirthDate(), "09/03/90");
		} catch (IOException | ParseException e) {
			fail(e.getMessage());
		}
				
	}

}
