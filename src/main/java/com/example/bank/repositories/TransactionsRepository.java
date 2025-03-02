package com.example.bank.repositories;

import com.example.bank.dtos.TransactionDTO;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

@ApplicationScoped
public class TransactionsRepository {

    private Map<Long, Set<TransactionDTO>> transactions;

    @PostConstruct
    public void init() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("CSV/transactions.csv")) {
            if (is != null) {
                Reader reader = new InputStreamReader(is);
                CsvToBeanBuilder<TransactionDTO> csvToBeanBuilder = new CsvToBeanBuilder<TransactionDTO>(reader)
                        .withType(TransactionDTO.class)
                        .withSkipLines(1);
                List<TransactionDTO> dtos = csvToBeanBuilder
                        .build()
                        .parse();
                transactions = dtos.stream().collect(groupingBy(TransactionDTO::getAccountId, toSet()));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Set<TransactionDTO> findAllByAccountIds(Set<Long> accountIds) {
        return transactions.entrySet().stream().
                filter(entry -> accountIds.contains(entry.getKey())).
                map(Map.Entry::getValue).
                flatMap(Set::stream).
                collect(Collectors.toSet());
//        return accountIds.stream().map(accountId -> transactionsions.get(accountId)).collect(Collectors.toSet());
    }


}
