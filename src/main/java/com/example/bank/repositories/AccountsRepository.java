package com.example.bank.repositories;

import com.example.bank.dtos.AccountDTO;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

@ApplicationScoped
public class AccountsRepository {

    private Map<Long, Set<AccountDTO>> accounts;

    @PostConstruct
    public void init() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("CSV/accounts.csv")) {
            if (is != null) {
                Reader reader = new InputStreamReader(is);
                CsvToBeanBuilder<AccountDTO> csvToBeanBuilder = new CsvToBeanBuilder<AccountDTO>(reader)
                        .withType(AccountDTO.class)
                        .withSkipLines(1);
                List<AccountDTO> dtos = csvToBeanBuilder
                        .build()
                        .parse();
                accounts = dtos.stream().collect(groupingBy(AccountDTO::getBeneficiaryId, toSet()));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Set<AccountDTO> findAllByBeneficiaryId(Long beneficiaryId) {
        return Optional.ofNullable(accounts.get(beneficiaryId)).orElse(Set.of());
    }
}
