package com.example.bank.resources;


import com.example.bank.dtos.AccountDTO;
import com.example.bank.services.AccountsService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.util.Set;

@Path("/accounts")
public class AccountsResource {

    @Inject
    AccountsService accountsService;

    @GET
    @Path("/{beneficiaryId}")
    @Produces("application/json")
    public Response findByBeneficiaryId(@PathParam("beneficiaryId") Long id) {
        Set<AccountDTO> accountDTOs = accountsService.findAllByBeneficiaryId(id);
        return Response.ok(accountDTOs).build();
    }

}

