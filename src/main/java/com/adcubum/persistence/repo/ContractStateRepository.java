package com.adcubum.persistence.repo;

import org.springframework.data.repository.CrudRepository;

import com.adcubum.persistence.entity.ContractState;

public interface ContractStateRepository extends CrudRepository<ContractState, String> {
}
