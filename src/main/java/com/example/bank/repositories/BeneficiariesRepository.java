package com.example.bank.repositories;


import com.example.bank.dtos.BeneficiaryDTO;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
public class BeneficiariesRepository {

    private Map<Long, BeneficiaryDTO> beneficiaries;

    @PostConstruct
    public void init() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("CSV/beneficiaries.csv")) {
            if (is != null) {
                Reader reader = new InputStreamReader(is);
                CsvToBeanBuilder<BeneficiaryDTO> csvToBeanBuilder = new CsvToBeanBuilder<BeneficiaryDTO>(reader)
                        .withType(BeneficiaryDTO.class)
                        .withSkipLines(1);
                List<BeneficiaryDTO> dtos = csvToBeanBuilder
                        .build()
                        .parse();
                beneficiaries = dtos.stream().collect(Collectors.toMap(BeneficiaryDTO::getBeneficiaryId, Function.identity()));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Optional<BeneficiaryDTO> findByBeneficiaryId(Long beneficiaryId) {
        return Optional.ofNullable(beneficiaries.get(beneficiaryId));
    }

}
