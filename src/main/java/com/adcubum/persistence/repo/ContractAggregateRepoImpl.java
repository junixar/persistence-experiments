package com.adcubum.persistence.repo;

import com.adcubum.persistence.entity.ContractState;

import java.time.LocalDate;
import java.util.Optional;

public class ContractAggregateRepoImpl implements ContractAggregateRepo {

    @Override
    public Optional<ContractState> findById(String contractId, LocalDate keyDate) {
        return Optional.empty();
    }

    @Override
    public ContractState save(ContractState contractState) {
        return null;
    }
}
