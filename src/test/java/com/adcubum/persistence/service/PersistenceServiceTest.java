package com.adcubum.persistence.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.adcubum.persistence.entity.Contract;

@SpringBootTest
@Transactional
@Rollback(false)
class PersistenceServiceTest {

   @Autowired
   private PersistenceService sut;

   @Autowired
   private EntityManager entityManager;

   @Test
   void findShouldFind() {
      //given
      UUID primaryKey = UUID.fromString("74f6ccdd-e68c-4f41-8d18-cdd5bd88b0a2");

      //when
      Optional<Contract> contract = sut.find(Contract.class, primaryKey);

      //then
      assertThat(contract).isNotEmpty();
      assertThat(contract.get().payload).isEqualTo("IPGvSB7fIuzwRF_0");
   }

   @Test
   void loadShouldLoad() {
      //given
      UUID primaryKey = UUID.fromString("74f6ccdd-e68c-4f41-8d18-cdd5bd88b0a2");

      //when
      Optional<Contract> contract = sut.load(primaryKey);

      //then
      assertThat(contract).isNotEmpty();
      assertThat(contract.get().payload).isEqualTo("IPGvSB7fIuzwRF_0");
   }

}