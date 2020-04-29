package com.adcubum.persistence.repo;

import com.adcubum.persistence.entity.Contract;
import com.adcubum.persistence.entity.ContractPart;
import com.adcubum.persistence.entity.ContractPartState;
import com.adcubum.persistence.entity.ContractState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContractAggregateRepoImplTest {

    @Autowired
    ContractAggregateRepoImpl sut;//subject under test

    @Test
    void saveShouldCreateNewStatesForContractAndContractParts() {
        //given

//        LocalDate

        ContractState contractState = new ContractState();
        Contract contract = new Contract();
        contractState.contract = contract;

        ContractPartState contractPartState1 = new ContractPartState();
        ContractPart contractPart1 = new ContractPart();
        contractPartState1.contractPart = contractPart1;
        contractPartState1.contract = contract;

        ContractPartState contractPartState2 = new ContractPartState();
        ContractPart contractPart2 = new ContractPart();
        contractPartState2.contractPart = contractPart2;
        contractPartState2.contract = contract;


        //when

        //then
    }

}