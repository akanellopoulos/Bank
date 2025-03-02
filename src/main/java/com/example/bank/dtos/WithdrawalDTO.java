package com.example.bank.dtos;

public class WithdrawalDTO {
    public WithdrawalDTO(Long beneficiaryId, Float withdrawal) {
        this.beneficiaryId = beneficiaryId;
        this.withdrawal = withdrawal;
    }

    private Long beneficiaryId;

    private Float withdrawal;

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public Float getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Float withdrawal) {
        this.withdrawal = withdrawal;
    }


}
