package com.adcubum.persistence.repo;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.transaction.annotation.Transactional;

import com.adcubum.persistence.entity.Contract;
import com.adcubum.persistence.entity.ContractState;

@SpringBootTest
@Transactional
@Rollback(false)
class ContractStateRepositoryTest {

   @Autowired
   private ContractStateRepository sut;

   @Autowired
   private EntityManager entityManager;

   @Test
   @Sql(statements = {
         "DELETE FROM contract_part_state",
         "DELETE FROM contract_part",
         "DELETE FROM contract_state",
         "DELETE FROM contract",
         "INSERT INTO contract VALUES('1', 'contractNo', '2020-01-01', '3000-01-01')",
         "INSERT INTO contract_state VALUES('1', 'offerNo', '2020-01-01', '3000-01-01', 1)",

         "INSERT INTO contract_part VALUES('1', 'productPartId1', '2020-01-01', '3000-01-01')",
         "INSERT INTO contract_part_state VALUES('1', 75, '2020-01-01', '3000-01-01', 1, 1)",

         "INSERT INTO contract_part VALUES('2', 'productPartId2', '2020-01-01', '3000-01-01')",
         "INSERT INTO contract_part_state VALUES('2', 25, '2020-01-01', '3000-01-01', 1, 2)"
   },
         config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
   void test() {
      Contract contract = entityManager.find(Contract.class, "1");
      ContractState contractState = sut.findById("1").get();
      contractState.contract.contractNo = "changedContractNo";
      sut.save(contractState);

      int size = contract.contractPartStates.size();

      if (contract.contractPartStates.size() == 0) {

      }


      assertThat(contract.contractPartStates).hasSize(2);
   }

}