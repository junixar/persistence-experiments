package com.adcubum.persistence.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adcubum.persistence.entity.Contract;

@Repository
public interface ContractRepo extends CrudRepository<Contract, String> {
}
