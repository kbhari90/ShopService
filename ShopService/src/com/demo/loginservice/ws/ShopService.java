package com.demo.loginservice.ws;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.demo.loginservice.database.UserDaoImpl;
import com.demo.loginservice.util.JacksonImpl;

@Path("/")
public class ShopService {
	private Map<String,String> resultMap;
	private String jsonFromObj;
	
	@GET
	@Path("getUserDetails/{param1}/{param2}")
	@Produces("application/json")
	public Response getUserDetails(@PathParam("param1") String userName, @PathParam("param2") String password) {
		resultMap = new UserDaoImpl().getCredentials(userName, password);
		if(resultMap == null){
			return Response.status(400).entity("One of the value is null").build();
		} else{
			jsonFromObj = new JacksonImpl().objToJson(resultMap);
		}
		
		return Response.ok(jsonFromObj, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("saveUserDetails/{param1}/{param2}")
	@Produces("application/text")
	public Response saveUserCreds(@PathParam("param1") String userName, @PathParam("param2") String password) {
		String response = new UserDaoImpl().saveCredentials(userName, password);
		if(response == null) {
			return Response.status(400).entity("Failed to Register").build();
		}else { 
			return Response.ok(response, MediaType.TEXT_PLAIN).build();
		}
	}
	
	
}
