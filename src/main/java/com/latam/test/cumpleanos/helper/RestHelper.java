package com.latam.test.cumpleanos.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.latam.test.cumpleanos.pojo.Person;
import com.latam.test.cumpleanos.pojo.Poet;
import com.latam.test.cumpleanos.pojo.Poem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestHelper {
	
	public Poem getPoema() throws IOException
	{
	    final String uri = "https://www.poemist.com/api/v1/randompoems";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	     
	    log.info("listado poemas: " +result);
	    
        ObjectMapper mapper = new ObjectMapper();

        
        List<Poem> poems = mapper.readValue(result, mapper.getTypeFactory().constructCollectionType
        		(List.class, Poem.class));
       
    	Random r = new Random();
    	int num = r.nextInt(poems.size());
        
     
        return poems.get(num);
        
                
        
	}


	public List<Person> getPersons(String json) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES,true);
        List<Person> persons = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType
        		(List.class, Person.class));
		return persons;
		
	}
}
