package com.latam.test.cumpleanos.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class Poem {
	
	@Getter
	@Setter
	@JsonProperty("title")
	private String title;
	
	@Getter
	@Setter
	@JsonProperty("content")
	private String content;
	
	@Getter
	@Setter
	@JsonProperty("url")
	private String url;
	
	@Getter
	@Setter
	@JsonProperty("poet")
	private Poet poet;

	@Override
	public String toString() {
	StringBuffer sb = new StringBuffer();
		
		sb.append(title);
		sb.append("\n");
		sb.append(content);
		sb.append("\n");
		sb.append(url);
		sb.append("\n");
		sb.append(poet);

	
		
		return sb.toString();
	}
	
	

}
