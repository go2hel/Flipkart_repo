package com.dropwizard.api;

import com.dropwizard.core.Customer;
import com.dropwizard.resources.constants.Role;
import com.dropwizard.service.UserOperation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/client")
public class ClientRestAPI {

    @GET
    @Path("/login")
    public Response login(@NotNull @QueryParam("id") String id,@NotNull @QueryParam("password") String password){
        try {
            Role role = UserOperation.getInstance().login(id,password);

            return Response.status(202).entity("logged in as: " + (role==Role.ADMIN ? "Admin":"Customer") + " Successfully").build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/signUp")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signUp(@Valid Customer customer){
        try {
            UserOperation.getInstance().signUp(customer);

            return Response.status(201).entity("Signed up Successfully").build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}
