package com.regofix.spannzangen.controller;

import com.regofix.spannzangen.model.Collet;
import com.regofix.spannzangen.service.ColletService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ColletController {

    private final ColletService colletService = new ColletService();

    @GET
    @Path("/spannzangen")
    public List<Collet> getAllCollets() {
        return colletService.getAllCollets();
    }

    @GET
    @Path("/spannzange/{id}")
    public Response getColletById(@PathParam("id") int id) {
        try {
            Collet collet = colletService.getColletById(id);
            return Response.ok(collet).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/spannzange")
    public Response addCollet(Collet collet) {
        try {
            Collet addedCollet = colletService.addCollet(collet);
            return Response.status(Response.Status.CREATED).entity(addedCollet).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/spannzange/{id}")
    public Response deleteCollet(@PathParam("id") int id) {
        try {
            colletService.deleteCollet(id);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
