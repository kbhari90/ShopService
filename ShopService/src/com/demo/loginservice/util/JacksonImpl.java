package com.demo.loginservice.util;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.demo.loginservice.beans.Credentials;

public class JacksonImpl {
	private Credentials creds;
	

	public String objToJson(Map<String,String> resultMap) {
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
		return null;
	}
}
