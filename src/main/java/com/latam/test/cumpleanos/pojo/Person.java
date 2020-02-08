package com.latam.test.cumpleanos.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Getter
	@Setter
	@JsonProperty("name")
	private String name;

	@Getter
	@Setter
	@JsonProperty("lastName")
	private String lastName;

	@Getter
	@Setter
	private Integer age;

	@Getter
	@Setter
	@JsonProperty("birthDate")
	private String birthDate;

	@Getter
	@Setter
	private String birthday;

	@Getter
	@Setter
	private Long days;

	@Getter
	@Setter
	private String poem;

	@Override
	public String toString() {
		return "Person [name=" + name + ", lastName=" + lastName + ", age=" + age + ", birthDate=" + birthDate
				+ ", birthday=" + birthday + ", days=" + days + ", poema=" + poem + "]";
	}

}