package com.example.bank.services;


import com.example.bank.dtos.AccountDTO;
import com.example.bank.repositories.AccountsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Set;


@ApplicationScoped
public class AccountsService {

    @Inject
    AccountsRepository accountsRepository;

    //Ανάκτηση των λογαριασμών ενός δικαιούχου δίνοντας την παράμετρο beneficiaryId
    public Set<AccountDTO> findAllByBeneficiaryId(Long beneficiaryId) {
        return accountsRepository.findAllByBeneficiaryId(beneficiaryId);
    }

}
