package com.regofix.spannzangen.controller;

import com.regofix.spannzangen.model.Collet;
import com.regofix.spannzangen.service.ColletService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

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
        Optional<Collet> collet = colletService.getColletById(id);
        if (collet.isPresent()) {
            return Response.ok(collet.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Collet with ID " + id + " not found")
                    .build();
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
        Optional<String> errorMessage = colletService.deleteCollet(id);
        if (errorMessage.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).entity(errorMessage.get()).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}
