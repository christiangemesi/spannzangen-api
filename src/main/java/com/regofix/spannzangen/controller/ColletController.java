package com.regofix.spannzangen.controller;


import com.regofix.spannzangen.StartupBean;
import com.regofix.spannzangen.model.Collet;
import com.regofix.spannzangen.repository.ColletRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


import java.util.List;

@Path("/spannzangen")
@Produces(MediaType.APPLICATION_JSON)
public class ColletController {

    @Inject
    private StartupBean startupBean;

    private final ColletRepository colletRepository = new ColletRepository();


    @GET
    public List<Collet> getAllCollets() {
        // Use the repository from the startup bean to return all collets
        return startupBean.getColletRepository().getAllCollets();
    }

}
