package com.latam.test.cumpleanos.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class Poet {

	@Getter
	@Setter
	@JsonProperty("name")
	private String name;
	
	@Getter
	@Setter
	@JsonProperty("url")
	private String url;

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(name);
		sb.append("\n");
		sb.append(url);
		
		return sb.toString();
	
	}
	
	
	
}
