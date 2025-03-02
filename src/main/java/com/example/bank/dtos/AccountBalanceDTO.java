package com.example.bank.dtos;

public class AccountBalanceDTO {
    private Long accountId;

    private Float balance;

    public AccountBalanceDTO(Long accountId, Float balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}
