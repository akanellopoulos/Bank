package com.example.bank.dtos;

import java.util.Set;

public class BalanceDTO {

    private Long beneficiaryId;

    private Set<AccountBalanceDTO> balances;

    public BalanceDTO(Long beneficiaryId, Set<AccountBalanceDTO> balances) {
        this.beneficiaryId = beneficiaryId;
        this.balances = balances;
    }

    public Set<AccountBalanceDTO> getBalances() {
        return balances;
    }

    public void setBalances(Set<AccountBalanceDTO> balances) {
        this.balances = balances;
    }

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

}
