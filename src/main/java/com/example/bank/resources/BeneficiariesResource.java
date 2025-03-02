package com.example.bank.resources;

import com.example.bank.dtos.BeneficiaryDTO;
import com.example.bank.services.BeneficiariesService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/beneficiaries")
public class BeneficiariesResource {

    @Inject
    BeneficiariesService beneficiariesService;

    @GET
    @Path("/{beneficiaryId}")
    @Produces("application/json")
    public Response findByBeneficiaryId(@PathParam("beneficiaryId") Long id) {
        Optional<BeneficiaryDTO> beneficiaryDTO = beneficiariesService.findByBeneficiaryId(id);
        return Response.ok(beneficiaryDTO.orElse(null)).build();
    }

}