package com.dropwizard.api;

import com.dropwizard.core.Customer;
import com.dropwizard.core.Item;
import com.dropwizard.service.AdminOperation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/admin")
public class AdminRestAPI {

    @GET
    @Path("/viewPending")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> viewPending(){
        return AdminOperation.getInstance().viewPending();
    }

    @PUT
    @Path("/approveCustomer")
    public Response approveCustomer(@NotNull @QueryParam("cid") String cid){
        try {
            AdminOperation.getInstance().approveCustomer(cid);

            return Response.status(200).entity("Customer with id " +cid + " approved.").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/banCustomer")
    public Response banCustomer(@NotNull @QueryParam("cid") String cid){
        try {
            AdminOperation.getInstance().banCustomer(cid);

            return Response.status(200).entity("Customer with id " +cid + " banned.").build();
        }catch (Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/addItem")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(@Valid Item item){
        try {
            AdminOperation.getInstance().addItem(item);

            return Response.status(200).entity("Item with name " + item.getName() + " added.").build();
        }catch (Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/addCount")
    public Response addCount(@NotNull @QueryParam("name") String name,@NotNull @QueryParam("count") int count){
        try {
            AdminOperation.getInstance().addItemCount(name, count);

            return Response.status(200).entity("Item with name " + name + " added.").build();
        }catch (Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/removeItem")
    public Response removeItem(@NotNull @QueryParam("name") String name){
        try {
            AdminOperation.getInstance().removeItem(name);

            return Response.status(200).entity("Item with name " + name + "removed.").build();
        }catch (Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/viewItems")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> viewItems(){
        return AdminOperation.getInstance().viewItems();
    }
}
