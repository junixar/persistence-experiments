package com.adcubum.persistence.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adcubum.persistence.entity.Contract;
import com.adcubum.persistence.repo.ContractRepo;

@Transactional
@Service
public class ContractService {

   @Autowired
   private ContractRepo contractRepo;

   public Optional<Contract> find(String id) {
      return contractRepo.findById(id);
   }

   public Contract save(Contract contract) {
      return contractRepo.save(contract);
   }

}
