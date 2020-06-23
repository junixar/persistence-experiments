package com.adcubum.persistence.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

import org.hibernate.Session;
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

   @Autowired
   private EntityManager entityManager;

   public Optional<Contract> find(String id) {
      EntityGraph<?> entityGraph = entityManager.getEntityGraph("contract");
      Map hints = new HashMap();
      hints.put("javax.persistence.fetchgraph", entityGraph);

      Session session = entityManager.unwrap(Session.class);
      session.enableFilter("state");

      entityManager.find(Contract.class, id, hints);

      Optional<Contract> contract = contractRepo.findById(id);
      contract.get().states.size();
      return contract;
   }

   public Contract save(Contract contract) {
      return contractRepo.save(contract);
   }

}
