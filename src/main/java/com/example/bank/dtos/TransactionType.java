package com.example.bank.dtos;

public enum TransactionType {
    withdrawal("withdrawal"),
    deposit("deposit");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
