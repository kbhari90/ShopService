package com.demo.loginservice.util;

import java.util.Map;





import org.apache.log4j.Logger;

import com.demo.loginservice.beans.Credentials;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JacksonImpl {
	private Credentials creds;
	private ObjectWriter writer;
	private String jsonFromObject;
	
	

	public String objToJson(Map<String,String> resultMap) {
		writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		creds = new Credentials();
		for(String key:resultMap.keySet()) {
			if(key.equalsIgnoreCase("userId")) {
				creds.setUserId(Integer.parseInt(resultMap.get(key)));
			}else if(key.equalsIgnoreCase("userName")) {
				creds.setUserName(resultMap.get(key));
			}else if(key.equalsIgnoreCase("password")) {
				creds.setPassword(resultMap.get(key));
			}else if(key.equalsIgnoreCase("creationDate")) {
				creds.setCreationDate(resultMap.get(key));
			}else if(key.equalsIgnoreCase("lastUpdateDate")) {
				creds.setLastUpdateDate(resultMap.get(key));
			}
		}
		/**
		 * Start from here 
		 * Return json from here
		 */
		try {
			
			jsonFromObject = writer.writeValueAsString(creds);
			
		} catch (JsonProcessingException e) {
			logger.debug("Error thrown while converting class object to json using jackson",e);
		}
		
		return jsonFromObject;
	}
	final static Logger logger = Logger.getLogger(JacksonImpl.class);
}
