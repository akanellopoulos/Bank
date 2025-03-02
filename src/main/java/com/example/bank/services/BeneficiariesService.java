package com.example.bank.services;

import com.example.bank.dtos.BeneficiaryDTO;
import com.example.bank.repositories.BeneficiariesRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class BeneficiariesService {

    @Inject
    BeneficiariesRepository beneficiariesRepository;

    //Ανάκτηση στοιχείων δικαιούχου δίνοντας την παράμετρο beneficiaryId
    public Optional<BeneficiaryDTO> findByBeneficiaryId(Long beneficiaryId) {
        return beneficiariesRepository.findByBeneficiaryId(beneficiaryId);
    }

}
