package com.dropwizard.api;

import com.dropwizard.core.Item;
import com.dropwizard.core.Notification;
import com.dropwizard.resources.constants.ModeOfPayment;
import com.dropwizard.service.CustomerOperation;
import com.dropwizard.service.NotificationOperation;
import com.dropwizard.service.PaymentOperation;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customer")
public class CustomerRestAPI {

    @GET
    @Path("/viewItems")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> viewItems(){
        return CustomerOperation.getInstance().viewItems();
    }

    @GET
    @Path("/viewCart")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> viewCart(@NotNull @QueryParam("id") String custID){
        return CustomerOperation.getInstance().viewCart(custID);
    }

    @POST
    @Path("/addItem")
    public Response addItem(@NotNull @QueryParam("id") String id, @NotNull @QueryParam("name") String name,
                            @NotNull @QueryParam("count") int count){
        try {
            CustomerOperation.getInstance().addItem(id,name,count);
            return Response.status(201).entity("Item: " + name + " added in total: "+count).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/removeItem")
    public Response removeItem(@NotNull @QueryParam("id") String id, @NotNull @QueryParam("name") String name){
        try {
            CustomerOperation.getInstance().removeItem(id, name);

            return Response.status(200).entity("Item: " + name + " removed").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/viewBalance")
    public float viewBalance(@NotNull @QueryParam("id") String custID){
        return CustomerOperation.getInstance().viewBalance(custID);
    }

    @PUT
    @Path("/addMoney")
    public Response addMoney(@NotNull @QueryParam("id") String id,@QueryParam("amount") float amount,
                             @NotNull @QueryParam("mode") ModeOfPayment mop){
        CustomerOperation.getInstance().addMoney(id,amount,mop);
        return Response.status(200).entity("Balance updated").build();
    }

    @PUT
    @Path("/checkOut")
    public Response checkOut(@NotNull @QueryParam("id") String id){
        try {
            PaymentOperation.getInstance().makePayment(id);

            return Response.status(200).entity("Payment made successfully").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/viewNotification")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Notification> viewNotification(@NotNull @QueryParam("id") String id){
        return NotificationOperation.getInstance().viewNotifications(id);
    }
}
