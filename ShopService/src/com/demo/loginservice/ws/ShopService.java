package com.demo.loginservice.ws;

import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.demo.loginservice.database.UserDaoImpl;
import com.demo.loginservice.util.JacksonImpl;

@Path("/")
public class ShopService {
	private Map<String,String> resultMap;
	private String jsonFromObj;
	
	@POST
	@Path("getUserDetails")
	@Produces("application/json")
	public Response getUserDetails(@FormParam("userName") String userName, @FormParam("password") String password) {
		resultMap = new UserDaoImpl().getCredentials(userName, password);
		if(resultMap == null){
			return Response.status(400).entity("One of the value is null").build();
		} else{
			jsonFromObj = new JacksonImpl().objToJson(resultMap);
		}
		
		return Response.ok(jsonFromObj, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("saveUserDetails")
	@Produces("application/text")
	public Response saveUserCreds(@FormParam("userName") String userName, @FormParam("password") String password) {
		String response = new UserDaoImpl().saveCredentials(userName, password);
		if(response == null) {
			return Response.status(400).entity("Failed to Register").build();
		}else { 
			return Response.ok(response, MediaType.TEXT_PLAIN).build();
		}
	}
	
	
}
