package com.demo.loginservice.ws;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.demo.loginservice.database.UserDaoImpl;

@Path("/services")
public class ShopService {
	private Map<String,String> resultMap;
	
	@GET
	@Path("/{param1}/{param2}")
	public Response check(@PathParam("param1") String userName, @PathParam("param2") String password) {
//		resultMap = new UserDaoImpl().getCredentials(userName, password);
//		if(resultMap.containsValue(null)){
//			return Response.status(400).entity("One of the value is null").build();
//		}
//		
		/**
		 * Start from here
		 */
		//To be returned after creating json
		return Response.status(200).entity("Hello "+userName+" "+password).build();
	}
	
}
