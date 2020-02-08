package com.latam.test.cumpleanos.services;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.latam.test.cumpleanos.helper.RestHelper;
import com.latam.test.cumpleanos.pojo.Person;
import com.latam.test.cumpleanos.pojo.Poem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BirthdayProcess {

	@Autowired
	RestHelper restHelper;
	


	public List<Person> personInformation(List<Person> persons) throws IOException, ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter birthFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
		LocalDate localDate = LocalDate.now();

		log.info("hoy " + localDate);

		for (Person person : persons) {

			String birthdayPerson = person.getBirthDate();
			log.info("cumple persona: " + birthdayPerson);
			LocalDate localCumple = LocalDate.parse(birthdayPerson, formatter);
			person.setBirthDate(localCumple.format(birthFormatter));
			person.setAge(this.personAge(localDate, localCumple));

			person.setDays(this.birthdayDays(localDate, localCumple));
			if (person.getDays() == 0) {
				person.setBirthday("SI");
				Poem poem = restHelper.getPoema();
				person.setPoem(poem.getContent());
			} else {
				person.setBirthday("NO");
			}

			log.info("recorrido: " + person);
		}
		return persons;
	}

	public int personAge(LocalDate today, LocalDate birthDate) {
		Period betweenDates = Period.between(birthDate, today);
		log.info("edad " + betweenDates.getYears());
		return betweenDates.getYears();
	}

	public long birthdayDays(LocalDate today, LocalDate birthday) {
		birthday = birthday.withYear(today.getYear());
		long dias = ChronoUnit.DAYS.between(today, birthday);
		if (dias < 0) {
			birthday = birthday.withYear(today.getYear() + 1);
			dias = ChronoUnit.DAYS.between(today, birthday);
		}
		return dias;
	}

}
