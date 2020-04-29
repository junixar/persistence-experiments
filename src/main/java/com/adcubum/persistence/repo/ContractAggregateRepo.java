package com.adcubum.persistence.repo;

import com.adcubum.persistence.entity.ContractState;

import java.time.LocalDate;
import java.util.Optional;

public interface ContractAggregateRepo {

    public Optional<ContractState> findById(String contractId, LocalDate keyDate);

    public ContractState save(ContractState contractState);

}
