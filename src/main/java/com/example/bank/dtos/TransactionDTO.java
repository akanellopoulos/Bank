package com.example.bank.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.util.Date;
import java.util.Objects;

public class TransactionDTO {

    @CsvBindByPosition(position = 0)
    private Long transactionId;

    @CsvBindByPosition(position = 1)
    private Long accountId;

    @CsvBindByPosition(position = 2)
    private Float amount;

    @CsvBindByPosition(position = 3)
    private String type;

    @CsvDate(value = "MM/dd/yy")
    @CsvBindByPosition(position = 4)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonIgnore
    public Float getAmountByType() {
        if (Objects.equals(this.type, "withdrawal"))
            return -amount;
        else
            return amount;
    }

}
