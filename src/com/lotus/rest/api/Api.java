package com.lotus.rest.api;

import java.io.IOException;
import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.lotus.phonebook.implementors.PhonebookImplementor;
import com.lotus.phonebook.implementors.PhonebookInterface;
import com.lotus.phonebook.beans.*;
import com.lotus.phonebook.customeexceptions.NameException;

@Path("/phonebook")
public class Api {
	private PhonebookInterface pImpl = new PhonebookImplementor();
	
	
	@Path("list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() throws JSONException, JsonGenerationException, JsonMappingException, IOException  {
		List <ContactCompany> cclist = pImpl.listContactWithCompany();
		ObjectMapper mapper = new ObjectMapper();
		String response = "{}";
		if(!cclist.isEmpty()) {
			response = mapper.writeValueAsString(cclist);
		}
		return Response.status(200).entity(response).build();
	}
	
	@Path("show/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response show(@PathParam("name")String name) throws JSONException, JsonGenerationException, JsonMappingException, IOException, NameException  {
		ContactCompany contactCompany = pImpl.getSpecificContact(name);
		ObjectMapper mapper = new ObjectMapper();
		String response = "{}";
		if(contactCompany != null) {
			response = mapper.writeValueAsString(contactCompany);
		}
		return Response.status(200).entity(response).build();
	}
	
	
	@Path("search/{query}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(@PathParam("query")String query) throws JSONException, JsonGenerationException, JsonMappingException, IOException  {
		List <ContactCompany> cclist = pImpl.listContactWithCompany(query);
		ObjectMapper mapper = new ObjectMapper();
		String response = "{}";
		if(!cclist.isEmpty()) {
			response = mapper.writeValueAsString(cclist);
		}
		return Response.status(200).entity(response).build();
	}

	@Path("create")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Contact contact) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		try {
			pImpl.createContact(contact.getName(),contact.getContactNo(), contact.getBirthdate() , "OLODI", contact.getIsVip());
			jsonObject.put("success", true);
		} catch (Exception e) {
			jsonObject.put("success", false);
			jsonObject.put("errorMessage", "Cannot save data.");
		}
		
		return Response.status(200).entity(jsonObject.toString()).build();
	}

}
