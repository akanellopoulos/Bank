package com.example.bank.resources;

import com.example.bank.dtos.BalanceDTO;
import com.example.bank.dtos.TransactionDTO;
import com.example.bank.dtos.WithdrawalDTO;
import com.example.bank.services.TransactionsService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.Set;

@Path("/transactions")
public class TransactionsResource {

    @Inject
    TransactionsService transactionsService;

    @GET
    @Path("/{beneficiaryId}")
    @Produces("application/json")
    public Response findByBeneficiaryId(@PathParam("beneficiaryId") Long beneficiaryId) {
        Set<TransactionDTO> transactions = transactionsService.findAllByBeneficiaryId(beneficiaryId);
        return Response.ok(transactions).build();
    }

    @GET
    @Path("/{beneficiaryId}/balances")
    @Produces("application/json")
    public Response findBalanceByBeneficiaryId(@PathParam("beneficiaryId") Long beneficiaryId) {
        BalanceDTO balance = transactionsService.findBalanceByBeneficiaryId(beneficiaryId);
        return Response.ok(balance).build();
    }

    @GET
    @Path("/{beneficiaryId}/maxWithdrawal")
    @Produces("application/json")
    public Response findBiggestLastMonthWithdrawalByBeneficiaryId(@PathParam("beneficiaryId") Long beneficiaryId) {
        WithdrawalDTO maxWithdrawal = transactionsService.findBiggestLastMonthWithdrawalByBeneficiaryId(beneficiaryId);
        return Response.ok(maxWithdrawal).build();
    }

}
