package com.example.bank.dtos;

import com.opencsv.bean.CsvBindByPosition;

public class AccountDTO {

    @CsvBindByPosition(position = 0)
    private Long accountId;

    @CsvBindByPosition(position = 1)
    private Long beneficiaryId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

}
