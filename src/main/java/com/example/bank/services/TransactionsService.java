package com.example.bank.services;

import com.example.bank.dtos.*;
import com.example.bank.repositories.TransactionsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

@ApplicationScoped
public class TransactionsService {

    @Inject
    AccountsService accountsService;

    @Inject
    TransactionsRepository transactionsRepository;

    //Ανάκτηση των συναλλαγών ενός δικαιούχου δίνοντας την παράμετρο beneficiaryId
    public Set<TransactionDTO> findAllByBeneficiaryId(Long beneficiaryId) {
        Set<AccountDTO> accountDTOS = accountsService.findAllByBeneficiaryId(beneficiaryId);
        return transactionsRepository.findAllByAccountIds(accountDTOS.stream().
                map(AccountDTO::getAccountId).collect(Collectors.toSet()));
    }

    //Ανάκτηση του υπολοίπου των λογαριασμών ενός ανθρώπου δίνοντας την παράμετρο beneficiaryId
    public BalanceDTO findBalanceByBeneficiaryId(Long beneficiaryId) {
        Set<TransactionDTO> transactionDTOs = findAllByBeneficiaryId(beneficiaryId);
        Map<Long, Double> accountIdsTransactions = transactionDTOs.stream()
                .collect(groupingBy(TransactionDTO::getAccountId, Collectors.summingDouble(TransactionDTO::getAmountByType)));

        Set<AccountBalanceDTO> balances = accountIdsTransactions.
                entrySet().
                stream().
                map(a -> new AccountBalanceDTO(a.getKey(), a.getValue().floatValue())).
                collect(toSet());


        return new BalanceDTO(beneficiaryId, balances);
    }

    //Ανάκτηση της μεγαλύτερης ανάληψης για έναν δικαιούχο τον τελευταίο μήνα δίνοντας την παράμετρο beneficiaryId
    public WithdrawalDTO findBiggestLastMonthWithdrawalByBeneficiaryId(Long beneficiaryId) {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        Set<TransactionDTO> transactionDTOs = findAllByBeneficiaryId(beneficiaryId);
        Float maxWithdrawal = transactionDTOs.stream()
                .filter(entry -> "withdrawal".equals(entry.getType()))
                .filter(entry -> {
                    LocalDate transactionDate = entry.getDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    return !transactionDate.isBefore(oneMonthAgo);
                }).map(TransactionDTO::getAmount)
                .max(Comparator.naturalOrder())
                .orElse(0.0f);

        return new WithdrawalDTO(beneficiaryId, maxWithdrawal);
    }

}


