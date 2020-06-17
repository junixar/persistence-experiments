package com.adcubum.persistence.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import javax.swing.text.html.Option;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adcubum.persistence.entity.Contract;
import com.adcubum.persistence.entity.ContractState;
import com.adcubum.persistence.entity.DataState;

@SpringBootTest
class ContractServiceTest {

   @Autowired
   ContractService sut;

   @Test
   void test() {
      //given
      Contract contract = new Contract();
      contract.id = UUID.randomUUID().toString();

      ContractState contractState1 = new ContractState();
      contractState1.id = UUID.randomUUID().toString();
      contractState1.processId = "Initial process";
      contractState1.dataState = DataState.ORIGINAL;
      contractState1.contract = contract;

      ContractState contractState2 = new ContractState();
      contractState2.id = UUID.randomUUID().toString();
      contractState2.processId = "Change process";
      contractState2.dataState = DataState.ABEYANCE;
      contractState2.contract = contract;

      HashSet<ContractState> states = new HashSet<>();
      states.add(contractState1);
      states.add(contractState2);

      contract.states = states;



      sut.save(contract);

      //when
      Optional<Contract> loadedContract = sut.find(contract.id);

      //then
      assertThat(loadedContract.get().states).hasSize(1);
   }

}