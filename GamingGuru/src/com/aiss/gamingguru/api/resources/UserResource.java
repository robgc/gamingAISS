package com.aiss.gamingguru.api.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import com.aiss.gamingguru.domain.User;
import com.aiss.gamingguru.repository.UserRepository;
import com.aiss.gamingguru.repository.UserRepositoryImpl;

@Path("/users")
public class UserResource {
	
	private static UserResource _instance = null;
	
	UserRepository repository;
	
	private UserResource() {
		repository = new UserRepositoryImpl();
	}
	
	public static UserResource getInstance() {
		if (_instance == null) {
			_instance = new UserResource();
		}
		return _instance;
	}
	
	@GET
	@Produces("application/json")
	public Collection<User> getAllUsers() {
		return repository.getAll();
	}
	
	@GET
	@Path("/{userID}")
	@Produces("application/json")
	public User get(@PathParam("userID") String id) {
		if (id == null || "".equals(id))
			throw new BadRequestException("The user ID must not be null");
		
		User u = repository.get(id);
		
		if (u == null)
			throw new NotFoundException("Unable to find the user with ID: "
										 + id);
		return u;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addUser(@Context UriInfo uriInfo, User u) {
		if (u.getId() == null || "".equals(u.getId()))
			throw new BadRequestException("The user ID must not be null");
		
		if (u.getSteamid() == null || "".equals(u.getSteamid()))
			throw new BadRequestException("The steam ID must not be null");
		
		if (u.getScore() == null || u.getScore() < 0.0)
			throw new BadRequestException("Invalid score");
		
		if (repository.get(u.getId()) != null)
			throw new BadRequestException("The user already exists");
		
		repository.put(u);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder()
						.path(this.getClass(), "get");
		URI uri = ub.build(u.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(u);
		return resp.build();
	}
	
	@PUT
	@Path("/{userID}")
	@Consumes("application/json")
	public Response update(@PathParam("userID") String id, User u) {
		if (u.getId() == null || "".equals(u.getId()))
			throw new BadRequestException("The user ID must not be null");
		
		User old = repository.get(id);
		if (old == null)
			throw new NotFoundException("The user with id: " + id +
										 " was not found");
		if (!old.getId().equals(u.getId()) || 
			!old.getSteamid().equals(u.getSteamid()))
			return Response.status(Status.CONFLICT).build();
		
		if (u.getScore() < 0.0)
			throw new BadRequestException("The new score must be " + 
										   "a positive value");
		
		repository.put(u);
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{userID}")
	public Response remove(@PathParam("userID") String id) {
		User u = repository.get(id);
		if (u == null)
			throw new NotFoundException("The user with ID " + id + " was "
										 + "not found");
		else
			repository.remove(u);
		return Response.noContent().build();
	}
}
